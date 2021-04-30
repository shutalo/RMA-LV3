package com.example.rma_lv2.persistence

import com.example.rma_lv2.model.Person

class PeopleRepository(private val peopleDao: PeopleDao) {

    fun getPeople(): List<Person> = peopleDao.getPeople()
    fun getPerson(id: Int): Person = peopleDao.getPerson(id)
    fun insert(person: Person) = peopleDao.insert(person)
    fun delete(person: Person) = peopleDao.delete(person)
    fun updateName(name: String, id: Int) {
        peopleDao.updateName(name, id)
    }
    fun updateDate(date: String, id: Int) {
        peopleDao.updateDate(date, id)
    }
    fun updateDescription(description: String, id: Int) {
        peopleDao.updateDescription(description, id)
    }
    fun updateImageUrl(imageUrl: String, id: Int) {
        peopleDao.updateImageUrl(imageUrl, id)
    }
    fun updateIds(removedId: Int){
        for(index in (removedId + 1)..(getPeople().size + 1)){
            peopleDao.updateId(index - 1, index)
        }
    }

    fun addQuotes(newQuotes: ArrayList<String>, id: Int) {
        peopleDao.addQuotes(newQuotes,id)
    }
}