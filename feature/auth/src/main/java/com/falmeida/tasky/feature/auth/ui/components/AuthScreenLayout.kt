package com.falmeida.tasky.feature.auth.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falmeida.tasky.designsystem.component.theme.TaskyBlack
import com.falmeida.tasky.designsystem.component.theme.TaskyLinkBlue
import com.falmeida.tasky.designsystem.component.theme.TaskyTheme
import com.falmeida.tasky.designsystem.component.theme.TaskyTypography
import com.falmeida.tasky.designsystem.component.theme.TaskyWhite

@Composable
fun AuthScreenLayout(
    title: String,
    bottomText: String,
    clickableText: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    onBottomTextClick: () -> Unit,
    formContent: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TaskyBlack),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = TaskyTypography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp) // pushes white form down like the mockup
                .background(
                    TaskyWhite,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            formContent()

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = TaskyBlack,
                    contentColor = TaskyWhite
                )
            ) {
                Text(buttonText)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = bottomText,
                    style = MaterialTheme.typography.labelSmall,

                    color = TaskyBlack.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = clickableText,
                    color = TaskyLinkBlue,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.clickable(onClick = onBottomTextClick)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenLayoutPreview() {
    TaskyTheme(useDarkTheme = false) {
        AuthScreenLayout(
            title = "Create your account",
            bottomText = "ALREADY HAVE AN ACCOUNT?",
            clickableText = "LOG IN",
            buttonText = "GET STARTED",
            onButtonClick = {},
            onBottomTextClick = {}
        ) {
            repeat(3) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Field $it") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}