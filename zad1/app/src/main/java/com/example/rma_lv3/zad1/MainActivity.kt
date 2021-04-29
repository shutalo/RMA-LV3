    package com.example.rma_lv3.zad1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rma_lv3.R
import com.example.rma_lv3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val PREFS_FILENAME = "AnimalCounter"
    private val COLOR_KEY = "color"
    private val COUNT_KEY = "count"
    private lateinit var mainBinding: ActivityMainBinding
    private var numberOfBirds: Int = 0
    private var backgroundColor: Int = R.color.white
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        sharedPrefs = AnimalCounterApp.application.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

        mainBinding.numberOfBirdsTV.setBackgroundResource(sharedPrefs.getInt(COLOR_KEY, R.color.white))
        numberOfBirds = sharedPrefs.getInt(COUNT_KEY, 0)

        mainBinding.numberOfBirdsTV.text = numberOfBirds.toString()

        mainBinding.redButton.setOnClickListener {
            mainBinding.numberOfBirdsTV.text = (++numberOfBirds).toString()
            backgroundColor = R.color.red
            mainBinding.numberOfBirdsTV.setBackgroundResource(backgroundColor)
        }
        mainBinding.greenButton.setOnClickListener {
            mainBinding.numberOfBirdsTV.text = (++numberOfBirds).toString()
            backgroundColor = R.color.green
            mainBinding.numberOfBirdsTV.setBackgroundResource(backgroundColor)
        }
        mainBinding.yellowButton.setOnClickListener {
            mainBinding.numberOfBirdsTV.text = (++numberOfBirds).toString()
            backgroundColor = R.color.yellow
            mainBinding.numberOfBirdsTV.setBackgroundResource(backgroundColor)
        }
        mainBinding.blueButton.setOnClickListener {
            mainBinding.numberOfBirdsTV.text = (++numberOfBirds).toString()
            backgroundColor = R.color.blue
            mainBinding.numberOfBirdsTV.setBackgroundResource(backgroundColor)
        }
        mainBinding.resetButton.setOnClickListener{
            numberOfBirds = 0
            mainBinding.numberOfBirdsTV.text = numberOfBirds.toString()
            backgroundColor = R.color.white
            mainBinding.numberOfBirdsTV.setBackgroundResource(backgroundColor)
        }

    }

    override fun onStop() {
        super.onStop()
        sharedPrefs.edit().also {
            it.putInt(COUNT_KEY, numberOfBirds)
            it.putInt(COLOR_KEY, backgroundColor)
        }.apply()
    }

}