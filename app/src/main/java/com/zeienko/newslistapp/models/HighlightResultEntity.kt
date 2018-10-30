package com.zeienko.newslistapp.models

data class HighlightResultEntity(
    var title: TitleEntity? = null,
    var url: UrlEntity? = null,
    var author: AuthorEntity? = null
)