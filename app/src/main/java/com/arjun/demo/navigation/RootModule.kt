package com.arjun.demo.navigation

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val RootModule = module {
    viewModelOf(::RootNavViewModel)
}