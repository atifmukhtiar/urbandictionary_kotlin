package com.jadgroup.urbankotlin.dagger

import com.jadgroup.urbankotlin.adapter.DictionaryAdapter
import com.jadgroup.urbankotlin.interfaces.AlbumAPIs
import com.jadgroup.urbankotlin.keys.KeysString
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(var baseUrl: String) {

    private var albumAPIs: AlbumAPIs? = null

    @Singleton
    @Provides
    fun getClient(): AlbumAPIs {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)

        return Retrofit.Builder()
            .baseUrl(KeysString.BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create<AlbumAPIs>(AlbumAPIs::class.java)
    }

    @Singleton
    @Provides
    fun getDictionaryAdapter(): DictionaryAdapter {
        return DictionaryAdapter()
    }

}