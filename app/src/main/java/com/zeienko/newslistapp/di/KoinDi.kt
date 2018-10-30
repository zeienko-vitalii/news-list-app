package com.zeienko.newslistapp.di

import android.preference.PreferenceManager
import com.zeienko.newslistapp.data.net.manager.NetworkManager
import com.zeienko.newslistapp.data.net.mapper.news.*
import com.zeienko.newslistapp.viewmodel.news.NewsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

fun getAllKoinModules() = listOf(
    appModule,
    netModule,
    viewModelModule,
    hackerNewsMapper,
    hitMapper,
    highlightResultMapper,
    titleMapper,
    urlMapper,
    authorMapper
)

val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(androidApplication()) }
}

val viewModelModule = module {
    viewModel { NewsListViewModel(get(), get()) }
}

val hackerNewsMapper = module {
    single {
        NewsDataModelToDomainModelMapper(get())
    }
}

val hitMapper = module {
    single {
        HitDataModelToDomainMapper(get())
    }
}
val highlightResultMapper = module {
    single {
        HighlightResultDataModelToDomainModelMapper(get(), get(), get())
    }
}

val authorMapper = module {
    single {
        AuthorDataModelToDomainModel()
    }
}
val urlMapper = module {
    single {
        UrlDataModelToDomainModelMapper()
    }
}
val titleMapper = module {
    single {
        TitleDataModelToDomainModelMapper()
    }
}

val netModule = module {
    single {
        NetworkManager.getAlgoliaApi()
    }
}
