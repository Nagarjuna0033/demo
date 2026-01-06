package com.arjun.demo.feature.home.di

import com.arjun.demo.feature.home.HomeViewModel
import com.arjun.demo.feature.home.banner.BannerWidgetViewModel
import com.arjun.demo.feature.home.list.ListWidgetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val HomeModule = module {
    viewModelOf(::HomeViewModel)
    viewModel { (instanceId: String) ->
        BannerWidgetViewModel(
            instanceId = instanceId,
            getBannerData = get()
        )
    }
    viewModel { (instanceId: String) ->
        ListWidgetViewModel(
            instanceId = instanceId,
            getListData = get()
        )
    }
}