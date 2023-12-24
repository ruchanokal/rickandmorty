package com.characters.rickandmorty

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickAndMortyApplication : Application() {

    companion object {
        private lateinit var instance: RickAndMortyApplication
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}