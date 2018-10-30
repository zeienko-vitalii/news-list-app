package com.zeienko.newslistapp.data.net.mapper.news

import com.zeienko.newslistapp.data.net.mapper.Mapper
import com.zeienko.newslistapp.data.net.models.news.HitBean
import com.zeienko.newslistapp.models.HitEntity

class HitDataModelToDomainMapper(
    private val highlightResultDataModelToDomainModelMapper:
    HighlightResultDataModelToDomainModelMapper
) : Mapper<HitBean, HitEntity> {
    override fun map(from: HitBean): HitEntity {
        val hit = HitEntity()
        hit.author = from.author
        hit.createdAt = from.createdAt
        hit.commentText = from.commentText
        hit.numComments = from.numComments
        from.highlightResult?.also {
            hit.highlightResult = highlightResultDataModelToDomainModelMapper.map(it)
        }
        hit.objectID = from.objectID
        hit.points = from.points
        hit.storyText = from.storyText
        hit.tags = from.tags
        hit.title = from.title
        hit.url = from.url
        hit.storyId = from.storyId
        hit.storyTitle = from.storyTitle
        hit.storyUrl = from.storyUrl
        hit.parentId = from.parentId
        hit.createdAtI = from.createdAtI
        return hit
    }
}
