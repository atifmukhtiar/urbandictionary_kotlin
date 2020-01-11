package com.jadgroup.urbankotlin.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jadgroup.urbankotlin.R
import com.jadgroup.urbankotlin.UrbanApplication
import com.jadgroup.urbankotlin.adapter.DictionaryAdapter
import com.jadgroup.urbankotlin.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var context: Context;
    lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var dictionaryAdapter: DictionaryAdapter

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
                mainActivityViewModel.getAlbumList(charSequence.toString())
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
        mainActivityViewModel = ViewModelProviders.of(this)
            .get<MainActivityViewModel>(MainActivityViewModel::class.java)
        mainActivityViewModel.getAlbumLiveData()
            .observe(context as LifecycleOwner, Observer {
                dictionaryAdapter.updateData(it)
                hideProgress()
            })
        mainActivityViewModel.getAlbumList("")
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
