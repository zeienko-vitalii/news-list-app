package com.zeienko.newslistapp.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeienko.newslistapp.R
import com.zeienko.newslistapp.models.HitEntity
import kotlinx.android.synthetic.main.news_item.view.*

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(hitEntity: HitEntity?) {
        if (hitEntity != null) {
            itemView.tvNewsTitle.text = hitEntity.title
            itemView.tvNewsCreateTime.text = hitEntity.createdAt
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
            return NewsViewHolder(view)
        }
    }
}