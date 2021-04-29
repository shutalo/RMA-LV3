package com.example.rma_lv2.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.rma_lv2.R
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.dialog_layout.view.*

class EditPersonFragment : Fragment() {

    private val quotes = mutableListOf<String>()
    private var information = mutableMapOf<String,String>()
    private var listener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_person, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageURLEditText.setText(arguments?.getString("imageURL"))
        dateEditText.setText(arguments?.getString("date"))
        nameEditText.setText(arguments?.getString("name"))
        descriptionEditText.setText(arguments?.getString("description"))

        setUpListeners()
    }

    companion object {
        fun newInstance() = EditPersonFragment()
    }

    fun setListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    private fun setUpListeners(){
        saveButton.setOnClickListener {

            updateInformation()
            listener?.invoke()
        }

        addQuoteButton.setOnClickListener { createDialogWithEditText() }
    }

    private fun updateInformation(){
        information["name"] = nameEditText.text.toString()
        information["date"] = dateEditText.text.toString()
        information["description"] = descriptionEditText.text.toString()
        information["imageURL"] = imageURLEditText.text.toString()
    }

    fun getNewQuotes(): ArrayList<String>{
        val quotesExtras = arrayListOf<String>()

        quotes.forEach(){
            quotesExtras.add(it)
        }

        return quotesExtras
    }

    fun getUpdatedInformation(): MutableMap<String,String>{
        return information
    }

    /**
     * izdvojiti u posebnu klasu
     */
    private fun createDialogWithEditText() {
        val builder = AlertDialog.Builder(this.requireContext())
        val inflater = layoutInflater
        builder.setTitle("Add quote")
        val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
        val editText  = dialogLayout.editText
        builder.setView(dialogLayout)
        builder.setPositiveButton("Add") {
                _, _ -> quotes.add(editText.text.toString())
            Toast.makeText(this.requireContext(),"Quote added!", Toast.LENGTH_SHORT).show()
        }
        builder.setCancelable(true)
        builder.show()
    }
}