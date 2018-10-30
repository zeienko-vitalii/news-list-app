package com.zeienko.newslistapp.data.net.mapper.news

import com.zeienko.newslistapp.data.net.mapper.Mapper
import com.zeienko.newslistapp.data.net.models.news.UrlBean
import com.zeienko.newslistapp.models.UrlEntity

class UrlDataModelToDomainModelMapper : Mapper<UrlBean, UrlEntity> {
    override fun map(from: UrlBean): UrlEntity {
        val url = UrlEntity()
        url.value = from.value
        url.matchLevel = from.matchLevel
        url.matchedWords = from.matchedWords
        return url
    }
}