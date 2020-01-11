package com.jadgroup.urbankotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jadgroup.urbankotlin.pojos.Album

@Dao
interface AlbumDao {

    @Query("Select * from Album where Album.word =:word")
    fun getAllAlbumbsByWord(word: String): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbumList(albumList: ArrayList<Album>)

    @Query("Delete From Album where Album.word=:word")
    fun deleteAll(word:String)
}