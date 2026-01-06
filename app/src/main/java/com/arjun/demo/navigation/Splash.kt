package com.arjun.demo.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.arjun.demo.core.ui.components.DemoScaffold
import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute

fun NavGraphBuilder.splashDestination() {
    composable<SplashRoute> { SplashScreen() }
}

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    DemoScaffold {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {}
    }
}