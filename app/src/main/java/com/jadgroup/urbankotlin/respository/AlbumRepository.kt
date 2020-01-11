package com.jadgroup.urbankotlin.respository

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var albumDao: AlbumDao
    private var albumList: LiveData<List<Album>> = MutableLiveData()

    @Inject
    lateinit var retroClient: AlbumAPIs

    init {
        application.getComponent().inject(this)
        albumDao = AlbumDatabase.getAppDataBase(application)?.getAlbumDao()!!
        getAlbumListByName("")
    }

    fun insertAlbumList(albumList: ArrayList<Album>) {
        Thread(Runnable {
            albumDao.insertAlbumList(albumList)
        }).start()
    }

    fun getAlbumListByName(word: String) {
        albumList = albumDao.getAllAlbumbsByWord(word)
        Thread(Runnable {
        }).start()
    }

    fun deleteAllByName(word: String) {
        Thread(Runnable {
            albumDao.deleteAll(word)
        }).start()
    }

    fun getAlbumList(): LiveData<List<Album>> {
        return albumList
    }


    fun getServerAlbumList(term: String) {
        retroClient.getAlbumList(term).enqueue(object : Callback<AlbumList> {
            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                when (response.code()) {
                    200 -> {
                        val listAlbumList = response.body()
                        /*  deleteAllByName(term)
                          insertAlbumList(listAlbumList?.albums as ArrayList<Album>)
                          getAlbumListByName(term)*/

                        Thread(Runnable {
                            albumDao.deleteAll(term)
                            albumDao.insertAlbumList(listAlbumList?.albums as ArrayList<Album>)
                            albumList = albumDao.getAllAlbumbsByWord(term)
                        }).start()
                    }
                }

            }

            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                Log.d("onFailure", "api failed");
                Thread(Runnable {
                    albumList = albumDao.getAllAlbumbsByWord(term)
                }).start()

            }
        })
    }

}