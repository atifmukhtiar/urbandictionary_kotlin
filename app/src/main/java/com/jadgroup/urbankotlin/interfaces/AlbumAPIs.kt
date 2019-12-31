package com.jadgroup.urbankotlin.interfaces

import com.jadgroup.urbankotlin.models.AlbumList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AlbumAPIs {

    @Headers(
        "x-rapidapi-host:mashape-community-urban-dictionary.p.rapidapi.com",
        "x-rapidapi-key:PijukZnDjhmsh7JMpD0hdTVZhIvyp1MIlamjsn9Ig8j9tra8kp"
    )
    @GET("define")//?term=wat
    fun getAlbumList(@Query("term") term: String): Call<AlbumList>
}