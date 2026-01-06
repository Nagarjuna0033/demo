package com.arjun.demo.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.domain.GetDashboardWidgetsUseCase
import com.arjun.demo.core.model.WidgetMeta
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class HomeViewModel(
    getDashboardWidgets: GetDashboardWidgetsUseCase
) : ViewModel() {

    val state: StateFlow<DashboardState> =
        getDashboardWidgets()
            .map { dataState ->
                DashboardState(
                    uiState = when (dataState) {
                        DataState.Loading -> DashboardUiState.Loading
                        DataState.Empty -> DashboardUiState.Empty
                        is DataState.Error ->
                            DashboardUiState.Error(dataState.message)
                        is DataState.Success ->
                            DashboardUiState.Success(dataState.data)
                    }
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = DashboardState(DashboardUiState.Loading)
            )
}

internal data class DashboardState (
    val uiState: DashboardUiState,
)

sealed class DashboardUiState {

    object Loading : DashboardUiState()

    data class Success(
        val widgets: List<WidgetMeta>
    ) : DashboardUiState()

    data class Error(
        val message: String
    ) : DashboardUiState()

    object Empty : DashboardUiState()
}