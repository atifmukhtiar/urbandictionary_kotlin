package com.jadgroup.urbankotlin.respository

import android.util.Log
import androidx.lifecycle.LiveData
import com.jadgroup.urbankotlin.UrbanApplication
import com.jadgroup.urbankotlin.database.AlbumDao
import com.jadgroup.urbankotlin.database.AlbumDatabase
import com.jadgroup.urbankotlin.interfaces.AlbumAPIs
import com.jadgroup.urbankotlin.pojos.Album
import com.jadgroup.urbankotlin.pojos.AlbumList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AlbumRepository(application: UrbanApplication) {

    lateinit var albumDao: AlbumDao
    lateinit var albumList: LiveData<ArrayList<Album>>

    init {
        //application.getComponent().inject(this)
        albumDao = AlbumDatabase.getAppDataBase(application)?.getAlbumDao()!!
        albumList = albumDao.getAllAlbumbsByWord("")
    }

    fun insertAlbumList(albumList: ArrayList<Album>) {
        albumDao.insertAlbumList(albumList)
    }


    fun getAlbumList(word:String):LiveData<ArrayList<Album>>{
        return albumDao.getAllAlbumbsByWord(word)
    }

    @Inject
    lateinit var retroClient: AlbumAPIs

    fun getServerAlbumList(term: String) {
        retroClient.getAlbumList(term).enqueue(object : Callback<AlbumList> {
            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                val listAlbumList = response.body()
                insertAlbumList(listAlbumList?.albums as ArrayList<Album>)
            }

            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                Log.d("onFailure", "");
            }
        })
    }

}