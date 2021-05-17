package com.globant.marvelcharacters

import android.app.Application
import com.globant.marvelcharacters.di.ApplicationComponent
import com.globant.marvelcharacters.di.DaggerApplicationComponent

class MarvelApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}