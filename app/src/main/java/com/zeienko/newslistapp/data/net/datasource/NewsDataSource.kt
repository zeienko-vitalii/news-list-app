package com.zeienko.newslistapp.data.net.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zeienko.newslistapp.data.net.api.AlgoliaApi
import com.zeienko.newslistapp.data.net.mapper.news.NewsDataModelToDomainModelMapper
import com.zeienko.newslistapp.data.net.models.state.State
import com.zeienko.newslistapp.models.HitEntity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class NewsDataSource(
    private val api: AlgoliaApi,
    private val compositeDisposable: CompositeDisposable,
    private var mapper: NewsDataModelToDomainModelMapper
) : PageKeyedDataSource<Int, HitEntity>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, HitEntity>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            api.getStories(0)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        val result = mapper.map(response)
                        callback.onResult(
                            result.hits ?: emptyList(),
                            null, 1
                        )
                    }, {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, HitEntity>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            api.getStories(params.key)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        val result = mapper.map(response)
                        callback.onResult(
                            result.hits ?: emptyList(),
                            params.key + 1
                        )
                    }, {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, HitEntity>) {
    }

    private fun updateState(state: State) = this.state.postValue(state)

    fun retry() {
        retryCompletable?.let {
            compositeDisposable.add(
                it
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}