package com.falmeida.tasky.feature.auth.ui.register.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, style = MaterialTheme.typography.bodyMedium) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (isValid) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Valid",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.colors(

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor =  Color.Transparent,
            errorIndicatorColor =  Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.error,

            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            errorTrailingIconColor = MaterialTheme.colorScheme.error
        )
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onVisibilityToggle: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Password", style = MaterialTheme.typography.bodyMedium) },
        singleLine = true,
                shape = MaterialTheme.shapes.large,

        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon: ImageVector =
                if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = onVisibilityToggle) {
                Icon(imageVector = icon, contentDescription = "Toggle password visibility")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor =  Color.Transparent,
            errorIndicatorColor =  Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.error,

            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            errorTrailingIconColor = MaterialTheme.colorScheme.error
        )
    )
}