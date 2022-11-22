package com.works.renta.di

import android.content.Context
import com.works.renta.presentation.FirstFragment
import com.works.renta.presentation.MainActivity
import com.works.renta.presentation.TariffsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RoomModule::class, DataRepositoryModule::class])
@AppScope
interface AppComponent {

    fun fragmentComponentFactory(): FragmentComponent.Factory

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}