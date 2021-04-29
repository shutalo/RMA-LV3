package com.example.rma_lv2.fragments

import com.example.rma_lv2.model.Person
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_lv2.persistence.PeopleRepository
import com.example.rma_lv2.R
import com.example.rma_lv2.activities.AddPersonActivity
import com.example.rma_lv2.adapters.PeopleRecyclerViewAdapter
import com.example.rma_lv2.adapters.PersonViewHolder
import com.example.rma_lv2.interfaces.PersonClickListener
import kotlinx.android.synthetic.main.fragment_people.*


class PeopleFragment : Fragment(), PersonClickListener {

    private var listener: (() -> Unit)? = null
    private val people = PeopleRepository
    private var position:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("imageURL")?.also { it1 -> position.let { it2 -> people.getPerson(it2).setImageURL(it1) } }
        arguments?.getString("name")?.also { it1 -> position.let { it2 -> people.getPerson(it2).setName(it1) } }
        arguments?.getString("description")?.also { it1 -> position.let { it2 -> people.getPerson(it2).setDescription(it1) } }
        arguments?.getString("date")?.also { it1 -> position.let { it2 -> people.getPerson(it2).setDate(it1) } }
        arguments?.getStringArrayList("quotes")?.also { it1 -> position.let { it2 -> people.getPerson(it2).addQuotes(it1) } }
        recyclerView.adapter?.notifyDataSetChanged()

        setUpRecyclerView()

        addPerson.setOnClickListener { addNewPerson() }

    }

    companion object {
        fun newInstance() = PeopleFragment()
        const val ADD_PERSON_REQUEST: Int = 1
    }

    fun setListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    private fun addNewPerson(){
        val newPersonIntent = Intent(this.context,AddPersonActivity::class.java)
        startActivityForResult(newPersonIntent, ADD_PERSON_REQUEST)
    }

    private fun setUpRecyclerView(){
        val listener = this
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = PeopleRecyclerViewAdapter(people, listener)
        }
    }

    override fun onRemoveImageClicked(position: Int) {
        if (position != RecyclerView.NO_POSITION){
            people.delete(position)
            recyclerView.adapter?.notifyItemRemoved(position)
        }
    }

    override fun onPersonImageClicked(position: Int) {
        Toast.makeText(this.context,people.getPerson(position).getRandomQuote(),Toast.LENGTH_SHORT).show()
    }

    override fun onEditImageClicked(position: Int) {
        this.position = position
        listener?.invoke()
    }

    fun getPerson(): Person {
        return people.getPerson(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.let {
            if(requestCode == ADD_PERSON_REQUEST && resultCode == Activity.RESULT_OK){
                val imageURL = data.extras?.getString("imageURL")
                val name = data.extras?.getString("name")
                val date = data.extras?.getString("date")
                val description = data.extras?.getString("description")
                val quotes = data.extras?.getStringArrayList("quotes")

                name?.let { date?.let { quotes?.let { description?.let { imageURL?.let {people.insert(Person(name,date,imageURL,description,quotes))} } } } }

                recyclerView.adapter?.notifyItemInserted(people.getLastIndex())
            }
        }
    }

}