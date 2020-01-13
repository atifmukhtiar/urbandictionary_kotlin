package com.jadgroup.urbankotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jadgroup.urbankotlin.R
import com.jadgroup.urbankotlin.pojos.Words
import kotlinx.android.synthetic.main.dictionary_adapter_item.view.*
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class DictionaryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var wordsList: ArrayList<Words> = ArrayList();
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
        return wordsList.size;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val album = wordsList.get(position)
        val viewHolder = holder as DictionaryViewHolder
        viewHolder.txtViewWord.text = album.word
        viewHolder.txtViewAuthor.text = album.author
        viewHolder.txtViewDefination.text = album.defination
        viewHolder.txtViewThumbsUp.text = album.thumbsUp.toString()
        viewHolder.txtViewThumbsDown.text = album.thumbsDown.toString()

    }

    fun updateData(wordsList: ArrayList<Words>) {
        this.wordsList.clear()
        this.wordsList.addAll(wordsList)
        notifyDataSetChanged()
    }

    fun sortList(isThumbsUp: Boolean) {
        this.isThumbsUp = isThumbsUp
        Collections.sort<Words>(wordsList, albumComparator)
        this.notifyDataSetChanged()
    }

    var albumComparator =
        Comparator<Words> { album1, album2 ->
            if (isThumbsUp) {
                album2.thumbsUp!! - album1.thumbsUp!!
            } else album2.thumbsDown!! - album1.thumbsDown!!
        }


    companion object class DictionaryViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val txtViewWord = rootView.txtViewWord
        val txtViewAuthor = rootView.txtViewAuthor
        val txtViewDefination = rootView.txtViewDefination
        val txtViewThumbsUp = rootView.txtViewThumbsUp
        val txtViewThumbsDown = rootView.txtViewThumbsDown
    }
}