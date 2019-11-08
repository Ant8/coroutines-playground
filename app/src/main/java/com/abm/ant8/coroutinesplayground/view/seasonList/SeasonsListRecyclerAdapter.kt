package com.abm.ant8.coroutinesplayground.view.seasonList

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SeasonsListRecyclerAdapter(private val clickHandler: (Int) -> Unit) : RecyclerView.Adapter<SeasonsListViewHolder>() {
    val seasonsList = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsListViewHolder =
        SeasonsListViewHolder(parent)

    override fun getItemCount(): Int = seasonsList.size

    override fun onBindViewHolder(holder: SeasonsListViewHolder, position: Int) {
        holder.bind(seasonsList[position], clickHandler)
    }
}