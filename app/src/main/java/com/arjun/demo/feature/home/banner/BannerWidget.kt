package com.arjun.demo.feature.home.banner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.demo.core.ui.components.DemoExploreCard
import com.arjun.demo.core.ui.components.DemoLoader
import com.arjun.demo.feature.home.toSectionTitle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun BannerWidget(
    instanceId: String,
) {
    val viewModel: BannerWidgetViewModel = koinViewModel(
        parameters = { parametersOf(instanceId) }
    )

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = instanceId.toSectionTitle(),
            style = MaterialTheme.typography.titleMedium
        )

        when (val bannerState = state.uiState) {

            BannerUiState.Loading -> DemoLoader()

            is BannerUiState.Success -> {
                val banners = bannerState.widgets

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = banners,
                        key = { banner -> banner.id }
                    ) { banner ->
                        DemoExploreCard(
                            title = banner.title,
                            subTitle = banner.subtitle,
                            icon = Icons.Default.Home,
                            onClick = {}
                        )
                    }
                }
            }

            is BannerUiState.Error -> {
                Text(
                    text = "Error loading ${instanceId.toSectionTitle()}",
                    color = MaterialTheme.colorScheme.error
                )
            }

            BannerUiState.Empty -> {
                Text("No banners available")
            }
        }
    }

}
