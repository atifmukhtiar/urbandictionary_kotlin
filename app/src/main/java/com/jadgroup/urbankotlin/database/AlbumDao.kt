package com.jadgroup.urbankotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.jadgroup.urbankotlin.pojos.Album

@Dao
interface AlbumDao {

    @Query("Select * from Album where Album.word =:word")
    fun getAllAlbumbsByWord(word: String): LiveData<ArrayList<Album>>

    fun insertAlbumList(albumList: ArrayList<Album>)
}