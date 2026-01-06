package com.arjun.demo.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RootNavViewModel() : ViewModel() {

    private val _state = MutableStateFlow<RootNavState>(RootNavState.Splash)
    val state: StateFlow<RootNavState> = _state.asStateFlow()

    init {
        observeUserData()
    }

    private fun observeUserData() {
        viewModelScope.launch {
            _state.value = RootNavState.Home
        }
    }
}


sealed class RootNavState {

    data object Splash : RootNavState()

    data object Home : RootNavState()
}