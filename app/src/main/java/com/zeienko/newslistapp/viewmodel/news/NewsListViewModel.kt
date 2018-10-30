package com.zeienko.newslistapp.viewmodel.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zeienko.newslistapp.data.net.api.AlgoliaApi
import com.zeienko.newslistapp.data.net.datasource.NewsDataSource
import com.zeienko.newslistapp.data.net.datasource.NewsDataSourceFactory
import com.zeienko.newslistapp.data.net.mapper.news.NewsDataModelToDomainModelMapper
import com.zeienko.newslistapp.data.net.models.state.State
import com.zeienko.newslistapp.models.HitEntity
import io.reactivex.disposables.CompositeDisposable

class NewsListViewModel(
    api: AlgoliaApi,
    mapper: NewsDataModelToDomainModelMapper
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val articlesDataSourceFactory: NewsDataSourceFactory
    private val pageSize = 20
    var newsList: LiveData<PagedList<HitEntity>>

    init {
        articlesDataSourceFactory = NewsDataSourceFactory(compositeDisposable, api, mapper)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .build()
        newsList = LivePagedListBuilder<Int, HitEntity>(articlesDataSourceFactory, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<NewsDataSource, State>(
        articlesDataSourceFactory.newsDataSourceLiveData,
        NewsDataSource::state
    )

    fun retry() {
        articlesDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}