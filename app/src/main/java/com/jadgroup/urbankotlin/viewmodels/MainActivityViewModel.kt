package com.jadgroup.urbankotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jadgroup.urbankotlin.UrbanApplication
import com.jadgroup.urbankotlin.pojos.Album
import com.jadgroup.urbankotlin.respository.AlbumRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    private val albumLiveData: MutableLiveData<ArrayList<Album>> = MutableLiveData()
    private lateinit var repository: AlbumRepository

    init {
        (application as UrbanApplication).getComponent().inject(this)
        repository = AlbumRepository(application)
    }

    fun setAlbumLiveData(albumLiveData: ArrayList<Album>) {
        this.albumLiveData.value = albumLiveData
    }

    fun getAlbumLiveData(): LiveData<List<Album>> {
        return repository.getAlbumList()
    }

    fun getRepositoryServerAlbumList(word: String) {
        repository.getServerAlbumList(word)
    }

}