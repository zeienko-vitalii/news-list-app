package com.zeienko.newslistapp.models

data class HitEntity(
    var createdAt: String? = null,
    var title: String? = null,
    var url: String? = null,
    var author: String? = null,
    var points: Int? = null,
    var storyText: String? = null,
    var commentText: String? = null,
    var tags: MutableList<String>? = null,
    var numComments: Int? = null,
    var objectID: String? = null,
    var highlightResult: HighlightResultEntity? = null
)