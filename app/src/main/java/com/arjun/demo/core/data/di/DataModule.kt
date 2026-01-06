package com.arjun.demo.core.data.di

import com.arjun.demo.core.data.repository.BannerRepository
import com.arjun.demo.core.data.repository.ListWidgetRepository
import com.arjun.demo.core.data.repositoryImpl.BannerRepositoryImpl
import com.arjun.demo.core.data.repositoryImpl.ListWidgetRepositoryImpl
import org.koin.dsl.module

val DataModule = module {
    single<ListWidgetRepository> { ListWidgetRepositoryImpl(get()) }

    single<BannerRepository> { BannerRepositoryImpl(get()) }
}