package com.example.rma_lv2.adapters

import com.example.rma_lv2.model.Person
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rma_lv2.R
import com.example.rma_lv2.databinding.PersonLayoutBinding
import com.example.rma_lv2.interfaces.PersonClickListener

class PersonViewHolder(itemView: View, private val listener: PersonClickListener) : RecyclerView.ViewHolder(itemView){

    fun bind(person: Person){
        val personBinding = PersonLayoutBinding.bind(itemView)
        personBinding.personNameTextView.text = person.name
        personBinding.personDateTextView.text = person.date
        personBinding.personDescriptionTexView.text = person.description
        personBinding.picture.setOnClickListener { listener.onPersonImageClicked(adapterPosition) }
        personBinding.remove.setOnClickListener { listener.onRemoveImageClicked(adapterPosition) }
        personBinding.edit.setOnClickListener { listener.onEditImageClicked(adapterPosition) }

        Glide.with(personBinding.root)
            .load(person.imageURL)
            .placeholder(R.drawable.anonymous)
            .centerCrop()
            .into(personBinding.picture)
    }

}