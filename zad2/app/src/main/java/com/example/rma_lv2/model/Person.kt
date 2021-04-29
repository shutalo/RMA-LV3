package com.example.rma_lv2.model

import com.example.rma_lv2.R

class Person() {
    /**
     * sredit malo klasu
     */

    var name: String = ""
        private set

    var imageURL: String = ""
        private set

    var date: String = ""
        private set

    var description: String = ""
        private set

    private val quotes = mutableListOf<String>()


    constructor(name: String, date: String, imageURL: String, description: String, quotes: ArrayList<String>) : this(){
        this.name = name
        this.date = date
        this.imageURL = imageURL
        this.description = description
        quotes.forEach(){
            this.quotes.add(it)
        }
    }

    fun getRandomQuote(): String{
        if(quotes.size == 0)
            return "There are no quotes for this person!"
        return quotes.random()
    }

    fun addQuotes(quotes: ArrayList<String>){
        quotes.forEach(){
            this.quotes.add(it)
        }
    }

    fun setImageURL(imageURL: String){
        this.imageURL = imageURL
    }

    fun setName(name: String){
        this.name = name
    }

    fun setDescription(description: String){
        this.description = description
    }

    fun setDate(date: String){
        this.date = date
    }
}