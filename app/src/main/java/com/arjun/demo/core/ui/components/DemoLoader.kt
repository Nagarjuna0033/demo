package com.arjun.demo.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DemoLoader() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}