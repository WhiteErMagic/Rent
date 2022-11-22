package com.works.renta.di

import android.content.Context
import com.works.renta.presentation.FirstFragment
import com.works.renta.presentation.TariffsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
interface FragmentComponent {

    fun inject(arg: FirstFragment)
    fun inject(arg: TariffsFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create():FragmentComponent
    }
}