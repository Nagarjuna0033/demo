package com.arjun.demo.core.ui.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScaffold(
    onNavigateBack: () -> Unit ={},
    topBarTitle: String ="",
    showNavigationIcon:Boolean=true,
    modifier: Modifier = Modifier,
    @DrawableRes brandIcon: Int? = null,
    bottomBar: @Composable () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    floatingActionButtonContent: FloatingActionButtonContent? = null,
    snackbarHost: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Scaffold(
        topBar = {
            if(topBarTitle.isNotEmpty() || brandIcon!=null){
                DemoRoundedTopAppBar(
                    brandIcon = brandIcon,
                    title = topBarTitle,
                    onNavigateBack = onNavigateBack,
                    actions = actions,
                    showNavigationIcon = showNavigationIcon
                )
            }
        },
        floatingActionButton = {
            floatingActionButtonContent?.let { content ->
                FloatingActionButton(
                    onClick = content.onClick,
                    contentColor = content.contentColor,
                    content = content.content,
                )
            }
        },
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        contentWindowInsets = WindowInsets(0.dp),
        containerColor = containerColor,
        contentColor = contentColor,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .imePadding()
                    .navigationBarsPadding()
                    .padding(top=4.dp)
            ) {
                content()
            }
        },
        modifier = modifier,
    )
}

data class FloatingActionButtonContent(
    val onClick: (() -> Unit),
    val contentColor: Color,
    val content: (@Composable () -> Unit),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoRoundedTopAppBar(
    title: String,
    onNavigateBack: () -> Unit,
    showNavigationIcon:Boolean=true,
    modifier: Modifier = Modifier,
    @DrawableRes brandIcon: Int? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            if (brandIcon == null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        },
        actions = actions,
        navigationIcon = {
            if (brandIcon != null) {
                Box(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Image(
                        painter = painterResource(brandIcon),
                        contentDescription = "Brand Icon",
                        modifier = Modifier
                            .size(120.dp, 40.dp)
                            .align(Alignment.TopStart),
                        contentScale = ContentScale.Fit
                    )
                }
            } else if(showNavigationIcon) {
                IconButton(
                    onClick = onNavigateBack,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                spotColor = MaterialTheme.colorScheme.onSurface,
                ambientColor = MaterialTheme.colorScheme.onSurface,
            )
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(MaterialTheme.colorScheme.surface),
    )
}