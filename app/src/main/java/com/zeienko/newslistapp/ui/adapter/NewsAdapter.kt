package com.zeienko.newslistapp.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zeienko.newslistapp.data.net.models.state.State
import com.zeienko.newslistapp.models.HitEntity
import com.zeienko.newslistapp.ui.adapter.viewholder.NewsViewHolder
import com.zeienko.newslistapp.ui.adapter.viewholder.FooterViewHolder

class NewsAdapter(private val retry: () -> Unit) :
    PagedListAdapter<HitEntity, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) {
            NewsViewHolder.create(parent)
        } else {
            FooterViewHolder.create(retry, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as NewsViewHolder).bind(getItem(position))
        } else {
            (holder as FooterViewHolder).bind(state)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    fun getHitCount(): Int {
        return super.getItemCount()
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}