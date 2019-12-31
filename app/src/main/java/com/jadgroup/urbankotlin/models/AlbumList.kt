package com.jadgroup.urbankotlin.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlbumList {

    @SerializedName("list")
    @Expose
    val albums: List<Album>? = null
}