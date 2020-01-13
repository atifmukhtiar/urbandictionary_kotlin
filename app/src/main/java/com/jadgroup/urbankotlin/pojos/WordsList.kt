package com.jadgroup.urbankotlin.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WordsList {

    @SerializedName("list")
    @Expose
    val words: List<Words>? = null
}