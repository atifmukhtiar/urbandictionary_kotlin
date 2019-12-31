package com.jadgroup.urbankotlin.networks

import com.jadgroup.urbankotlin.interfaces.AlbumAPIs
import com.jadgroup.urbankotlin.keys.KeysString
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroClient {

    private var albumAPIs: AlbumAPIs? = null

    fun getClient(): AlbumAPIs? {

        val okHttpClient=OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(5,TimeUnit.MINUTES)

        if (albumAPIs == null) {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(KeysString.BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            albumAPIs = retrofit.create<AlbumAPIs>(AlbumAPIs::class.java)
        }
        return albumAPIs
    }

}