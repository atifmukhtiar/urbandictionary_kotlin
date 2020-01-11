package com.jadgroup.urbankotlin.dagger

import com.jadgroup.urbankotlin.ui.MainActivity
import com.jadgroup.urbankotlin.viewmodels.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetoworkComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivityViewModel: MainActivityViewModel)
}