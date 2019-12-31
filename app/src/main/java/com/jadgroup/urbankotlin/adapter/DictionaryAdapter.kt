package com.jadgroup.urbankotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadgroup.urbankotlin.R
import com.jadgroup.urbankotlin.models.Album
import kotlinx.android.synthetic.main.dictionary_adapter_item.view.*
import java.lang.String
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class DictionaryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var albumList: ArrayList<Album> = ArrayList();
    private var isThumbsUp = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DictionaryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.dictionary_adapter_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return albumList.size;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val album = albumList.get(position)
        val viewHolder = holder as DictionaryViewHolder
        viewHolder.txtViewWord.text = album.word
        viewHolder.txtViewAuthor.text = album.author
        viewHolder.txtViewDefination.text = album.defination
        viewHolder.txtViewThumbsUp.text = String.valueOf(album.thumbsUp)
        viewHolder.txtViewThumbsDown.text = String.valueOf(album.thumbsDown)

    }

    fun updateData(albumList: ArrayList<Album>) {
        this.albumList.clear()
        this.albumList.addAll(albumList)
        notifyDataSetChanged()
    }

    fun sortList(isThumbsUp: Boolean) {
        this.isThumbsUp = isThumbsUp
        Collections.sort<Album>(albumList, albumComparator)
        this.notifyDataSetChanged()
    }

    var albumComparator =
        Comparator<Album> { album1, album2 ->
            if (isThumbsUp) {
                album2.thumbsUp!! - album1.thumbsUp!!
            } else album2.thumbsDown!! - album1.thumbsDown!!
        }


    class DictionaryViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val txtViewWord = rootView.txtViewWord
        val txtViewAuthor = rootView.txtViewAuthor
        val txtViewDefination = rootView.txtViewDefination
        val txtViewThumbsUp = rootView.txtViewThumbsUp
        val txtViewThumbsDown = rootView.txtViewThumbsDown
    }
}