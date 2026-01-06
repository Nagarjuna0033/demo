package com.arjun.demo.feature.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arjun.demo.core.model.WidgetMeta
import com.arjun.demo.core.ui.components.DemoLoader
import com.arjun.demo.core.ui.components.DemoScaffold
import com.arjun.demo.feature.home.banner.BannerWidget
import com.arjun.demo.feature.home.list.ListWidget
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val dashboardState by homeViewModel.state.collectAsStateWithLifecycle()


    HomeScreenContent(
        uiState = dashboardState.uiState
    )

}


@Composable
internal fun HomeScreenContent(
    uiState: DashboardUiState
) {
    DemoScaffold(
        topBarTitle = "Dashboard",
        showNavigationIcon = false,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            when (uiState) {

                DashboardUiState.Loading -> DemoLoader()

                DashboardUiState.Empty -> {
                    Text(
                        text = "No widgets available",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is DashboardUiState.Error -> {
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is DashboardUiState.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = uiState.widgets,
                            key = { widget ->
                                widget.instanceId
                            }
                        ) { widget ->

                            when (widget) {
                                is WidgetMeta.Banner ->
                                    BannerWidget(instanceId = widget.instanceId)

                                is WidgetMeta.List ->
                                    ListWidget(instanceId = widget.instanceId)
                            }
                        }
                    }
                }
            }
        }
    }


}


fun String.toSectionTitle(): String =
    replaceFirstChar { it.uppercaseChar() }

