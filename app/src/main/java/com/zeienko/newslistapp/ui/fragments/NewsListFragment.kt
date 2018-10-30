package com.zeienko.newslistapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeienko.newslistapp.R
import com.zeienko.newslistapp.data.net.models.state.State
import com.zeienko.newslistapp.ui.adapter.NewsAdapter
import com.zeienko.newslistapp.viewmodel.news.NewsListViewModel
import kotlinx.android.synthetic.main.news_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment() {
    companion object {
        fun newInstance() = NewsListFragment()
    }

    private val newsListViewModel: NewsListViewModel by viewModel()
    private lateinit var articlesAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        tvErrorLoading.setOnClickListener { newsListViewModel.retry() }
        observeNewsList()
        observeStateOfFetchingData()
    }

    private fun observeStateOfFetchingData() {
        newsListViewModel.getState().observe(this, Observer { state ->
            if ((newsListViewModel.listIsEmpty() && state == State.LOADING)) {
                pbNewsLoading.visible()
            } else {
                pbNewsLoading.gone()
            }
            if (newsListViewModel.listIsEmpty() && state == State.ERROR) {
                tvErrorLoading.visible()
            } else {
                tvErrorLoading.gone()
            }
            if (!newsListViewModel.listIsEmpty()) {
                articlesAdapter.setState(state ?: State.DONE)
                updateTitle()
            }
        })
    }

    private fun updateTitle() {
        (activity as AppCompatActivity).supportActionBar?.title =
                "News: ${articlesAdapter.getHitCount()}"
    }

    private fun observeNewsList() {
        newsListViewModel.newsList.observe(this, Observer {
            articlesAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        val viewManager = LinearLayoutManager(activity)
        articlesAdapter = NewsAdapter { newsListViewModel.retry() }
        rvNewsList.apply {
            layoutManager = viewManager
            adapter = articlesAdapter
            addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        }
    }

    fun View.visible(): View {
        this.visibility = View.VISIBLE
        return this
    }

    fun View.gone(): View {
        this.visibility = View.GONE
        return this
    }
}