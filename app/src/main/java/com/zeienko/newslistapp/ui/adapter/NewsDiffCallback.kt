package com.zeienko.newslistapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zeienko.newslistapp.models.HitEntity

class NewsDiffCallback : DiffUtil.ItemCallback<HitEntity>() {
    override fun areItemsTheSame(oldItem: HitEntity, newItem: HitEntity): Boolean {
        return oldItem.objectID == newItem.objectID
    }

    override fun areContentsTheSame(oldItem: HitEntity, newItem: HitEntity): Boolean {
        return oldItem == newItem
    }
}