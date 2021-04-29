package com.example.rma_lv2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rma_lv2.R
import com.example.rma_lv2.fragments.EditPersonFragment
import com.example.rma_lv2.fragments.PeopleFragment

class MainActivity : AppCompatActivity() {

    private val peopleFragment = PeopleFragment.newInstance()
    private val editPersonFragment = EditPersonFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        peopleFragment.setListener {
            provideItemClickedData()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,editPersonFragment).commit()
        }

        editPersonFragment.setListener {
            updateData()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,peopleFragment).commit()
        }

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,peopleFragment).commit()
    }

    private fun updateData() {
        val bundle = Bundle()
        val information = editPersonFragment.getUpdatedInformation()
        val quotes = editPersonFragment.getNewQuotes()

        bundle.putString("name",information["name"])
        bundle.putString("date",information["date"])
        bundle.putString("imageURL",information["imageURL"])
        bundle.putString("description",information["description"])
        bundle.putStringArrayList("quotes",quotes)

        peopleFragment.arguments = bundle
    }

    private fun provideItemClickedData() {
        val bundle = Bundle()
        peopleFragment.getPerson().also { it ->
            bundle.putString("name",it.name)
            bundle.putString("date",it.date)
            bundle.putString("imageURL",it.imageURL)
            bundle.putString("description",it.description)
        }
        editPersonFragment.arguments = bundle
    }
}


