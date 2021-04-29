package com.example.rma_lv2.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.rma_lv2.R
import kotlinx.android.synthetic.main.activity_add_person.*
import kotlinx.android.synthetic.main.dialog_layout.view.*

class AddPersonActivity : AppCompatActivity() {

    private val quotes = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        setUpListeners()
    }

    private fun setUpListeners(){
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val date = dateEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val imageURL = imageURLEditText.text.toString()
            val quotesExtras = arrayListOf<String>()

            intent.putExtra("name", name)
            intent.putExtra("date", date)
            intent.putExtra("description", description)
            intent.putExtra("imageURL", imageURL)
            quotes.forEach {
                quotesExtras.add(it)
            }
            intent.putStringArrayListExtra("quotes", quotesExtras)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        addQuoteButton.setOnClickListener { createDialogWithEditText() }
    }


    private fun createDialogWithEditText() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setTitle("Add quote")
        val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
        val editText  = dialogLayout.editText
        builder.setView(dialogLayout)
        builder.setPositiveButton("Add") {
                _, _ -> quotes.add(editText.text.toString())
            Toast.makeText(this,"Quote added!", Toast.LENGTH_SHORT).show()
        }
        builder.setCancelable(true)
        builder.show()
    }
}