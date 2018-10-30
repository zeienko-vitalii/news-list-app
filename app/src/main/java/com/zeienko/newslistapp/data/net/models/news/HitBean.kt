package com.zeienko.newslistapp.data.net.models.news

import com.google.gson.annotations.SerializedName

data class HitBean constructor(
    @SerializedName("created_at")
    val createdAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val author: String? = null,
    val points: Int? = null,
    @SerializedName("story_text")
    val storyText: String? = null,
    @SerializedName("comment_text")
    val commentText: String? = null,
    @SerializedName("num_comments")
    val numComments: Int? = null,
    @SerializedName("story_id")
    val storyId: String? = null,
    @SerializedName("story_title")
    val storyTitle: String? = null,
    @SerializedName("story_url")
    val storyUrl: String? = null,
    @SerializedName("parent_id")
    val parentId: String? = null,
    @SerializedName("created_at_i")
    val createdAtI: Long? = null,
    @SerializedName("_tags")
    val tags: MutableList<String>? = null,
    val objectID: String? = null,
    @SerializedName("_highlightResult")
    val highlightResult: HighlightResultBean? = null
)