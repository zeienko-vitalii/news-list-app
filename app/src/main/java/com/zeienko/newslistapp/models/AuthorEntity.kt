package com.zeienko.newslistapp.models

data class AuthorEntity(
    var value: String? = null,
    var matchLevel: String? = null,
    var matchedWords: MutableList<String>? = null
)