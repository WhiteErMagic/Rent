package com.works.renta.di

import androidx.lifecycle.ViewModel
import com.works.renta.presentation.FirstFragmentViewModel
import com.works.renta.presentation.TariffsFragmentViewModel
import com.works.renta.presentation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(FirstFragmentViewModel::class)
    @Binds
    fun bindFirstFragmentViewModel(impl: FirstFragmentViewModel): ViewModel

    @IntoMap
    @ViewModelKey(TariffsFragmentViewModel::class)
    @Binds
    fun bindTariffsFragmentViewModel(impl: TariffsFragmentViewModel): ViewModel
}