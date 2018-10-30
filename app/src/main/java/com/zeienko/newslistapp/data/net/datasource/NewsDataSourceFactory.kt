package com.zeienko.newslistapp.data.net.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.zeienko.newslistapp.data.net.api.AlgoliaApi
import com.zeienko.newslistapp.data.net.mapper.news.NewsDataModelToDomainModelMapper
import com.zeienko.newslistapp.models.HitEntity
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val api: AlgoliaApi,
    private var mapper: NewsDataModelToDomainModelMapper
) : DataSource.Factory<Int, HitEntity>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, HitEntity> {
        val newsDataSource = NewsDataSource(api, compositeDisposable, mapper)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}