package com.jadgroup.urbankotlin.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jadgroup.urbankotlin.UrbanApplication
import com.jadgroup.urbankotlin.interfaces.AlbumAPIs
import com.jadgroup.urbankotlin.pojos.Words
import com.jadgroup.urbankotlin.pojos.WordsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    init {
        (application as UrbanApplication).getComponent().inject(this)
    }

    private val wordsLiveData: MutableLiveData<ArrayList<Words>> = MutableLiveData()

    fun setAlbumLiveData(wordsLiveData: ArrayList<Words>) {
        this.wordsLiveData.value = wordsLiveData
    }

    fun getAlbumLiveData(): MutableLiveData<ArrayList<Words>> {
        return wordsLiveData
    }


    @Inject
    lateinit var retroClient: AlbumAPIs
    fun getAlbumList(term: String) {
        retroClient.getAlbumList(term).enqueue(object : Callback<WordsList> {
            override fun onResponse(call: Call<WordsList>, response: Response<WordsList>) {
                val listAlbumList = response.body()
                setAlbumLiveData(listAlbumList?.words as ArrayList<Words>)
            }

            override fun onFailure(call: Call<WordsList>, t: Throwable) {
                Log.d("onFailure", "");
            }
        })
    }
}