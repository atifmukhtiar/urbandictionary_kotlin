package com.jadgroup.urbankotlin.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Album(

    @SerializedName("definition")
    @Expose
    val defination: String? = null,
    @SerializedName("permalink")
    @Expose
    val permalink: String? = null,
    @SerializedName("thumbs_up")
    @Expose
    val thumbsUp: Int? = null,
    @SerializedName("sound_urls")
    @Expose
    val soundUrls: List<String>? = null,
    @SerializedName("author")
    @Expose
    val author: String? = null,
    @SerializedName("word")
    @Expose
    val word: String? = null,
    @SerializedName("defid")
    @Expose
    val defid: Int? = null,
    @SerializedName("current_vote")
    @Expose
    val currentVote: String? = null,
    @SerializedName("written_on")
    @Expose
    val writtenOn: String? = null,
    @SerializedName("example")
    @Expose
    val example: String? = null,
    @SerializedName("thumbs_down")
    @Expose
    val thumbsDown: Int? = null

)