package com.zeienko.newslistapp.models

data class TitleEntity(
    var value: String? = null,
    var matchLevel: String? = null,
    var matchedWords: MutableList<String>? = null
)