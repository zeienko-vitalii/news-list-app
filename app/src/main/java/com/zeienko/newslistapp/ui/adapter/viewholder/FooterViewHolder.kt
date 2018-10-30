package com.zeienko.newslistapp.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zeienko.newslistapp.R
import com.zeienko.newslistapp.data.net.models.state.State
import kotlinx.android.synthetic.main.footer_item.view.*

class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(state: State?) {
        itemView.pbFooterItem.visibility = if (state == State.LOADING) VISIBLE else View.INVISIBLE
        itemView.tvErrorFooterItem.visibility = if (state == State.ERROR) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): FooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.footer_item, parent, false)
            view.tvErrorFooterItem.setOnClickListener { retry() }
            return FooterViewHolder(view)
        }
    }
}