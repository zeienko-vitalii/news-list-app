package com.zeienko.newslistapp.data.net.models.news

data class TitleBean(
    val value: String? = null,
    val matchLevel: String? = null,
    val matchedWords: MutableList<String>? = null
)