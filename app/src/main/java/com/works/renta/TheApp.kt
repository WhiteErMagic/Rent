package com.works.renta

import android.app.Application
import com.works.renta.di.AppScope
import com.works.renta.di.DaggerAppComponent

class TheApp: Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}