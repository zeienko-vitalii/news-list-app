package com.zeienko.newslistapp.data.net.mapper.news

import com.zeienko.newslistapp.data.net.mapper.Mapper
import com.zeienko.newslistapp.data.net.models.news.NewsBean
import com.zeienko.newslistapp.models.HitEntity
import com.zeienko.newslistapp.models.NewsEntity
import java.util.*

class NewsDataModelToDomainModelMapper(private val hitMapper: HitDataModelToDomainMapper) :
    Mapper<NewsBean, NewsEntity> {
    override fun map(from: NewsBean): NewsEntity {
        val data = NewsEntity()
        val listHit = from.hits
        val listDomainHit: MutableList<HitEntity> = LinkedList()
        listHit?.let {
            for (item in it) {
                listDomainHit.add(hitMapper.map(item))
            }
        }
        data.hits = listDomainHit
        data.hitsPerPage = from.hitsPerPage
        data.nbHits = from.nbHits
        data.nbPages = from.nbPages
        data.page = from.page
        data.params = from.params
        data.processingTimeMS = from.processingTimeMS
        data.query = from.query
        data.exhaustiveNbHits = from.exhaustiveNbHits
        return data
    }
}