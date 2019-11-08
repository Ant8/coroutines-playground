package com.abm.ant8.coroutinesplayground.view.seasonList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abm.ant8.coroutinesplayground.R
import kotlinx.android.synthetic.main.season_list_item.view.*

class SeasonsListViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.season_list_item, parent, false)) {

    fun bind(seasonYear: Int, clickHandler: (Int) -> Unit) {
        with(itemView.seasonYearTextView) {
            text = "$seasonYear"
            setOnClickListener { clickHandler(seasonYear) }
        }
    }
}