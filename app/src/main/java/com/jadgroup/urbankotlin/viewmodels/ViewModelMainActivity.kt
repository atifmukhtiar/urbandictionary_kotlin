package com.jadgroup.urbankotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jadgroup.urbankotlin.models.Album

class ViewModelMainActivity : ViewModel() {
    private val albumLiveData: MutableLiveData<ArrayList<Album>> = MutableLiveData()

    fun setAlbumLiveData(albumLiveData: ArrayList<Album>) {
        this.albumLiveData.value = albumLiveData
    }

    fun getAlbumLiveData(): MutableLiveData<ArrayList<Album>> {
        return albumLiveData
    }
}