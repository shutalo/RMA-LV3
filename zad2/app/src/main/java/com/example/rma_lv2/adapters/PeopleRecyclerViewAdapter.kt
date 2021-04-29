package com.example.rma_lv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rma_lv2.R
import com.example.rma_lv2.databinding.PersonLayoutBinding
import com.example.rma_lv2.interfaces.PersonClickListener
import com.example.rma_lv2.persistence.PeopleRepository
import kotlinx.android.synthetic.main.person_layout.view.*

class PeopleRecyclerViewAdapter(private val people: PeopleRepository, private val listener: PersonClickListener) : RecyclerView.Adapter<PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_layout, parent, false)
        return PersonViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return people.getPeople().size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(people.getPerson(position))
    }

}