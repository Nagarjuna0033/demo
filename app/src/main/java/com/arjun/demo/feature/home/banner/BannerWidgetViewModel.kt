package com.arjun.demo.feature.home.banner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.domain.GetBannerDataUseCase
import com.arjun.demo.core.model.BannerConfig
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class BannerWidgetViewModel(
    instanceId: String,
    getBannerData: GetBannerDataUseCase
) : ViewModel() {

    val uiState: StateFlow<BannerWidgetState> =
        getBannerData(instanceId)
            .map { dataState ->
                BannerWidgetState(
                    uiState = when (dataState) {
                        DataState.Loading -> BannerUiState.Loading
                        DataState.Empty -> BannerUiState.Empty
                        is DataState.Error ->
                            BannerUiState.Error(dataState.message)
                        is DataState.Success ->
                            BannerUiState.Success(dataState.data)
                    }
                )

            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                BannerWidgetState(BannerUiState.Loading)
            )
}

internal data class BannerWidgetState (
    val uiState: BannerUiState,
)

sealed class BannerUiState {

    object Loading : BannerUiState()

    data class Success(
        val widgets: List<BannerConfig>
    ) : BannerUiState()

    data class Error(
        val message: String
    ) : BannerUiState()

    object Empty : BannerUiState()
}
