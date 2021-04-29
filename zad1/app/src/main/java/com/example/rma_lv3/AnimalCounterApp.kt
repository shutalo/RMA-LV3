package com.example.rma_lv3

import android.app.Application

class AnimalCounterApp: Application() {

    companion object{
        lateinit var application: AnimalCounterApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}