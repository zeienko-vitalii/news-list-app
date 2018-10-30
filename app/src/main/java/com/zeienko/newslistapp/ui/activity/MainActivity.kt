package com.zeienko.newslistapp.ui.activity

import androidx.fragment.app.Fragment
import com.zeienko.newslistapp.ui.fragments.NewsListFragment

class MainActivity : AbstractActivity() {
    override fun createFragment(): Fragment {
        return NewsListFragment.newInstance()
    }
}
