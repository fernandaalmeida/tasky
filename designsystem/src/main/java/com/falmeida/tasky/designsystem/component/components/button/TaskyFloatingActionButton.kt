package com.falmeida.tasky.designsystem.component.components.button

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.falmeida.tasky.designsystem.component.theme.TaskyBlack
import com.falmeida.tasky.designsystem.component.theme.TaskyWhite

@Composable
fun TaskyFloatingActionButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    backgroundColor: Color = fabContainerColor(),
    contentColor: Color = fabContentColor(),
    contentDescription: String // 👈 required for a11y
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .size(size.coerceAtLeast(68.dp))
            .semantics {
                this.contentDescription = contentDescription
                this.role = Role.Button
            },
        containerColor = backgroundColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.size(size / 2)) {
            icon()
        }
    }
}

@Composable
private fun fabContainerColor(): Color {
    val isDark = isSystemInDarkTheme()
    return if (isDark) TaskyWhite else TaskyBlack
}

@Composable
private fun fabContentColor(): Color {
    val isDark = isSystemInDarkTheme()
    return if (isDark) TaskyBlack else TaskyWhite
}