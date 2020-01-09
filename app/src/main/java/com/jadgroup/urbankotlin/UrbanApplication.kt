package com.jadgroup.urbankotlin

import android.app.Application
import com.jadgroup.urbankotlin.dagger.DaggerNetoworkComponent
import com.jadgroup.urbankotlin.dagger.NetoworkComponent
import com.jadgroup.urbankotlin.dagger.NetworkModule
import com.jadgroup.urbankotlin.keys.KeysString

class UrbanApplication : Application() {


    lateinit var networkComponent: NetoworkComponent

    override fun onCreate() {
        super.onCreate()
        networkComponent = DaggerNetoworkComponent.builder()
            .networkModule(NetworkModule(KeysString.BASE_URL))
            .build();
    }

    fun getComponent(): NetoworkComponent {
        return networkComponent
    }
}