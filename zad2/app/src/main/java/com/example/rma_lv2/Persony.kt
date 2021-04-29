package com.example.rma_lv2

import android.app.Application

class Persony : Application() {

    companion object{
        lateinit var application: Persony
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}