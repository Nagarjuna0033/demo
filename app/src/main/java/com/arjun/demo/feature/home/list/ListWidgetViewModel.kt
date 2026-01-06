package com.arjun.demo.feature.home.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.domain.GetListDataUseCase
import com.arjun.demo.core.model.ListWidgetConfig
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class ListWidgetViewModel(
    instanceId: String,
    getListData: GetListDataUseCase
) : ViewModel() {

    val uiState: StateFlow<ListWidgetState> =
        getListData(instanceId)
            .map { dataState ->
                ListWidgetState(
                    uiState = when (dataState) {
                        DataState.Loading -> ListUiState.Loading
                        DataState.Empty -> ListUiState.Empty
                        is DataState.Error ->
                            ListUiState.Error(dataState.message)
                        is DataState.Success ->
                            ListUiState.Success(dataState.data)
                    }
                )

            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                ListWidgetState(ListUiState.Loading)
            )
}

internal data class ListWidgetState (
    val uiState: ListUiState,
)

sealed class ListUiState {

    object Loading : ListUiState()

    data class Success(
        val widgets: List<ListWidgetConfig>
    ) : ListUiState()

    data class Error(
        val message: String
    ) : ListUiState()

    object Empty : ListUiState()
}
