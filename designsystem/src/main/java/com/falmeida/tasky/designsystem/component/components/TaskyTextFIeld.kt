//package com.falmeida.tasky.designsystem.component
//
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.sp
//
//
//@Composable
//private fun TaskyTextField(
//    value: String,
//    onValueChange: (String) -> Unit,
//    label: String,
//    singleLine: Boolean,
//    keyboardType: KeyboardType = KeyboardType.Text,
//    isPassword: Boolean = false,
//    passwordVisible: Boolean = false,
//    onVisibilityToggle: () -> Unit = {}
//) {
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        label = { Text(label) },
//        modifier = Modifier.fillMaxWidth(),
//        singleLine = singleLine,
//        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
//        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
//        keyboardOptions = KeyboardOptions.Default.copy(
//            keyboardType = keyboardType,
//            imeAction = ImeAction.Next
//        ),
//        trailingIcon = {
//            if (isPassword) {
//                val icon: ImageVector =
//                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
//                IconButton(onClick = onVisibilityToggle) {
//                    Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
//                }
//            }
//        }
//    )
//}