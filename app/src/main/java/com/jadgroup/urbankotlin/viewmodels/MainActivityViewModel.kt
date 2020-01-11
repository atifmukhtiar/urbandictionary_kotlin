package com.jadgroup.urbankotlin.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jadgroup.urbankotlin.UrbanApplication
import com.jadgroup.urbankotlin.interfaces.AlbumAPIs
import com.jadgroup.urbankotlin.pojos.Album
import com.jadgroup.urbankotlin.pojos.AlbumList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    init {
        (application as UrbanApplication).getComponent().inject(this)
    }

    private val albumLiveData: MutableLiveData<ArrayList<Album>> = MutableLiveData()

    fun setAlbumLiveData(albumLiveData: ArrayList<Album>) {
        this.albumLiveData.value = albumLiveData
    }

    fun getAlbumLiveData(): MutableLiveData<ArrayList<Album>> {
        return albumLiveData
    }


    @Inject
    lateinit var retroClient: AlbumAPIs
    fun getAlbumList(term: String) {
        retroClient.getAlbumList(term).enqueue(object : Callback<AlbumList> {
            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                val listAlbumList = response.body()
                setAlbumLiveData(listAlbumList?.albums as ArrayList<Album>)
            }

            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                Log.d("onFailure", "");
            }
        })
    }
}