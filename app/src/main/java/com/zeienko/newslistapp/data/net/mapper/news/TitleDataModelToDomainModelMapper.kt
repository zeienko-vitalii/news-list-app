package com.zeienko.newslistapp.data.net.mapper.news

import com.zeienko.newslistapp.data.net.mapper.Mapper
import com.zeienko.newslistapp.data.net.models.news.TitleBean
import com.zeienko.newslistapp.models.TitleEntity

class TitleDataModelToDomainModelMapper : Mapper<TitleBean, TitleEntity> {
    override fun map(from: TitleBean): TitleEntity {
        val title = TitleEntity()
        title.matchLevel = from.matchLevel
        title.matchedWords = from.matchedWords
        title.value = from.value
        return title
    }
}