package com.zeienko.newslistapp.data.net.mapper.news

import com.zeienko.newslistapp.data.net.mapper.Mapper
import com.zeienko.newslistapp.data.net.models.news.AuthorBean
import com.zeienko.newslistapp.models.AuthorEntity

class AuthorDataModelToDomainModel : Mapper<AuthorBean, AuthorEntity> {
    override fun map(from: AuthorBean): AuthorEntity {
        val author = AuthorEntity()
        author.matchLevel = from.matchLevel
        author.matchedWords = from.matchedWords
        author.value = from.value
        return author
    }
}