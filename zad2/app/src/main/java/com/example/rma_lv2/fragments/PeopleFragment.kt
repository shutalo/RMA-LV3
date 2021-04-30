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
import com.example.rma_lv2.persistence.PeopleDatabaseBuilder
import kotlinx.android.synthetic.main.fragment_people.*


class PeopleFragment : Fragment(), PersonClickListener {

    private var listener: (() -> Unit)? = null
    private val peopleDao = PeopleDatabaseBuilder.getInstance().peopleDao()
    private val people = PeopleRepository(peopleDao)
    private var position:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("imageURL")?.let { people.updateImageUrl(it,getClickedPersonId()) }
        arguments?.getString("name")?.let { people.updateName(it,getClickedPersonId()) }
        arguments?.getString("description")?.let { people.updateDescription(it,getClickedPersonId()) }
        arguments?.getString("date")?.let { people.updateDate(it,getClickedPersonId()) }
        arguments?.getStringArrayList("quotes")?.let {
            val newQuotes = people.getPerson(getClickedPersonId()).quotes
            it.forEach(){
                newQuotes.add(it)
            }
            people.addQuotes(newQuotes,getClickedPersonId())
        }
        //arguments?.getStringArrayList("quotes")?.also { it1 -> position.let { it2 -> people.getPerson(it2).addQuotes(it1) } }
        (recyclerView.adapter as? PeopleRecyclerViewAdapter)?.refreshData(people.getPeople())

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
            adapter = PeopleRecyclerViewAdapter(peopleDao.getPeople(), listener)
        }
    }

    override fun onRemoveImageClicked(position: Int) {
        if (position != RecyclerView.NO_POSITION){
            this.position = position
            people.delete(people.getPerson(getClickedPersonId()))
            people.updateIds(getClickedPersonId())
            (recyclerView.adapter as PeopleRecyclerViewAdapter).refreshData(people.getPeople())
        }
    }

    private fun getClickedPersonId(): Int{
        return position + 1
    }

    override fun onPersonImageClicked(position: Int) {
        Toast.makeText(this.context,people.getPerson(getClickedPersonId()).quotes.random(),Toast.LENGTH_SHORT).show()
    }

    override fun onEditImageClicked(position: Int) {
        this.position = position
        listener?.invoke()
    }

    fun getPerson(): Person {
        return people.getPerson( getClickedPersonId())
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

                name?.let { date?.let { quotes?.let { description?.let { imageURL?.let {people.insert(Person(people.getPeople().size + 1,name,imageURL,date,description,quotes))} } } } }

                (recyclerView.adapter as PeopleRecyclerViewAdapter).refreshData(people.getPeople())
            }
        }
    }

}