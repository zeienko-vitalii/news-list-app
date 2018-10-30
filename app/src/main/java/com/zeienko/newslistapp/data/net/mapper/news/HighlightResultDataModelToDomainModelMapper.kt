package com.zeienko.newslistapp.data.net.mapper.news

import com.zeienko.newslistapp.data.net.mapper.Mapper
import com.zeienko.newslistapp.data.net.models.news.HighlightResultBean
import com.zeienko.newslistapp.models.HighlightResultEntity

class HighlightResultDataModelToDomainModelMapper(
    private val authorMapper: AuthorDataModelToDomainModel,
    private val urlMapper: UrlDataModelToDomainModelMapper,
    private val titleMapper: TitleDataModelToDomainModelMapper
) : Mapper<HighlightResultBean, HighlightResultEntity> {
    override fun map(from: HighlightResultBean): HighlightResultEntity {
        val highlightResult = HighlightResultEntity()
        from.apply {
            author?.let { author ->
                highlightResult.author = authorMapper.map(author)
            }
            title?.let { title ->
                highlightResult.title = titleMapper.map(title)
            }
            url?.let { url ->
                highlightResult.url = urlMapper.map(url)
            }
        }
        return highlightResult
    }
}