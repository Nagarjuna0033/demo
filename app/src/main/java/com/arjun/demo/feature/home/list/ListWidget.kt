package com.arjun.demo.feature.home.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.demo.core.ui.components.DemoExploreCard
import com.arjun.demo.core.ui.components.DemoLoader
import com.arjun.demo.feature.home.banner.BannerWidgetViewModel
import com.arjun.demo.feature.home.toSectionTitle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun    ListWidget(
    instanceId: String,
) {
    val viewModel: ListWidgetViewModel = koinViewModel(
        parameters = { parametersOf(instanceId) }
    )

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Text(
        text = instanceId.toSectionTitle(),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(
            vertical = 12.dp
        )
    )

    when (val listUiState = state.uiState) {
        ListUiState.Loading -> DemoLoader()

        is ListUiState.Success -> {
            val list = listUiState.widgets
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                list.forEach { item ->

                    DemoExploreCard(
                        title = item.name,
                        subTitle = item.surname,
                        icon = Icons.Default.Home,
                        onClick = { }
                    )
                }
            }
        }

        is ListUiState.Error -> {
            Text("Error loading banner")
        }

        ListUiState.Empty -> {
            Text("No banners")
        }

    }
}
