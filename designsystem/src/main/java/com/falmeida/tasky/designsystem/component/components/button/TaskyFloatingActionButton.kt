package com.falmeida.tasky.designsystem.component.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TaskyFloatingActionButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    contentDescription: String = "",
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
    size: Dp = 56.dp
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = backgroundColor,
        contentColor = contentColor
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier.size(size)
        ) {
            icon()
        }
    }
}