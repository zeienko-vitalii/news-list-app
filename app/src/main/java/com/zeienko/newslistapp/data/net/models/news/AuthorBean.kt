package com.zeienko.newslistapp.data.net.models.news

data class AuthorBean(
    val value: String? = null,
    val matchLevel: String? = null,
    val matchedWords: MutableList<String>? = null
)