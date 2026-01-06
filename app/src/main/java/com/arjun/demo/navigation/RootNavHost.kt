package com.arjun.demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.arjun.demo.core.ui.theme.DemoTheme
import com.arjun.demo.feature.home.HomeRoute
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.compose.NavHost
import com.arjun.demo.feature.home.homeDestination

@Composable
fun RootNavScreen(
    modifier: Modifier = Modifier,
    viewModel: RootNavViewModel = koinViewModel(),
) {
    val navController = rememberNavController()

    val state by viewModel.state.collectAsStateWithLifecycle()

    val startDestination = when (state) {
        RootNavState.Home -> HomeRoute
        RootNavState.Splash -> SplashRoute
    }

    DemoTheme {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            splashDestination()

            homeDestination()
        }
    }
}