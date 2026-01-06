package com.arjun.demo.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MifosCustomCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    variant: CardVariant = CardVariant.FILLED,
    shape: Shape? = null,
    colors: CardColors? = null,
    elevation: CardElevation? = null,
    borderStroke: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    when (variant) {
        CardVariant.FILLED -> Card(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape ?: CardDefaults.shape,
            colors = colors ?: CardDefaults.cardColors(),
            elevation = elevation ?: CardDefaults.cardElevation(),
            border = borderStroke,
            interactionSource = interactionSource,
        ) {
            content()
        }

        CardVariant.ELEVATED -> ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape ?: CardDefaults.elevatedShape,
            colors = colors ?: CardDefaults.elevatedCardColors(),
            elevation = elevation ?: CardDefaults.elevatedCardElevation(),
            interactionSource = interactionSource,
        ) {
            content()
        }

        CardVariant.OUTLINED -> OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape ?: CardDefaults.outlinedShape,
            colors = colors ?: CardDefaults.outlinedCardColors(),
            elevation = elevation ?: CardDefaults.outlinedCardElevation(),
            border = borderStroke ?: CardDefaults.outlinedCardBorder(enabled),
            interactionSource = interactionSource,
        ) {
            content()
        }
    }
}

@Composable
fun DemoExploreCard(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    multiline: Boolean = true,
    maxLines: Int = 2,
    onClick: () -> Unit,
) {
    MifosCustomCard(
        modifier = modifier
            .height(125.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(25.dp),
            ),
        variant = CardVariant.ELEVATED,
        elevation = CardDefaults.elevatedCardElevation(15.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Image(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(35.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            )

            Text(
                text = if (multiline) {
                    text.replaceFirst(" ", "\n")
                } else {
                    text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = maxLines,
                overflow = TextOverflow.MiddleEllipsis,
                textAlign = TextAlign.Start,
            )
        }
    }
}


@Preview
@Composable
private fun Demo_Explore_Card_Preview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            // Method 1: Automatic multiline (spaces become line breaks)
            DemoExploreCard(
                modifier = Modifier.weight(0.5f, true),
                icon = Icons.Default.Home,
                text = "Home Loan",
                onClick = { /* Handle click */ },
            )

            // Method 2: Custom line breaks with explicit control
            DemoExploreCard(
                modifier = Modifier.weight(0.5f, true),
                icon = Icons.Default.Home,
                text = "Personal Loan",
                onClick = { /* Handle click */ },
            )
        }

        // Method 3: Single line version
        DemoExploreCard(
            icon = Icons.Default.Home,
            text = "Savings Account",
            multiline = true,
            onClick = { /* Handle click */ },
        )

        DemoExploreCard(
            icon = Icons.Default.Home,
            text = "Business Account",
            multiline = true,
            onClick = { /* Handle click */ },
        )
    }
}


enum class CardVariant {

    FILLED,

    ELEVATED,

    OUTLINED,
}