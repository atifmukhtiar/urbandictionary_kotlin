package com.jadgroup.urbankotlin.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jadgroup.urbankotlin.R
import com.jadgroup.urbankotlin.UrbanApplication
import com.jadgroup.urbankotlin.adapter.DictionaryAdapter
import com.jadgroup.urbankotlin.interfaces.AlbumAPIs
import com.jadgroup.urbankotlin.models.Album
import com.jadgroup.urbankotlin.models.AlbumList
import com.jadgroup.urbankotlin.viewmodels.ViewModelMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var context: Context? = null

    var viewModelMainActivity = ViewModelMainActivity()

    @Inject
    lateinit var dictionaryAdapter: DictionaryAdapter

    @Inject
    lateinit var retroClient: AlbumAPIs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this;
        (application as UrbanApplication).getComponent().inject(this)
        initResources();
        initViewModel();
        setOnClickListener()

    }


    private fun initResources() {
        initRecyclerView();
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                getAlbumList(charSequence.toString())
                showProgress()
            }

            override fun afterTextChanged(editable: Editable) {
                txtView_thumbsUp.isSelected = false
                txtView_thumbsDown.isSelected = false
            }
        })
    }

    private fun initRecyclerView() {
        rv_dictionary.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dictionaryAdapter
        }

    }

    private fun initViewModel() {
        viewModelMainActivity = ViewModelProviders.of(this)
            .get<ViewModelMainActivity>(ViewModelMainActivity::class.java)
        viewModelMainActivity.getAlbumLiveData()
            .observe(context as LifecycleOwner, Observer {
                dictionaryAdapter.updateData(it)
                hideProgress()
            })
        getAlbumList("")
    }

    fun getAlbumList(term: String) {
        retroClient.getAlbumList(term).enqueue(object : Callback<AlbumList> {
            override fun onResponse(call: Call<AlbumList>, response: Response<AlbumList>) {
                val listAlbumList = response.body()
                viewModelMainActivity.setAlbumLiveData(listAlbumList?.albums as ArrayList<Album>)
            }

            override fun onFailure(call: Call<AlbumList>, t: Throwable) {
                Log.d("onFailure", "");
            }
        })
    }

    private fun setOnClickListener() {
        txtView_thumbsUp.setOnClickListener(this);
        txtView_thumbsDown.setOnClickListener(this);
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.txtView_thumbsUp -> {
                txtView_thumbsUp.isSelected = true
                txtView_thumbsDown.isSelected = false
                dictionaryAdapter.sortList(true)
            }
            R.id.txtView_thumbsDown -> {
                txtView_thumbsUp.isSelected = false
                txtView_thumbsDown.isSelected = true
                dictionaryAdapter.sortList(false)

            }
        }
    }

    fun showProgress() {
        progress_circular.setVisibility(View.VISIBLE)
        rv_dictionary.setVisibility(View.GONE)
    }

    fun hideProgress() {
        progress_circular.setVisibility(View.GONE)
        rv_dictionary.setVisibility(View.VISIBLE)
    }
}
