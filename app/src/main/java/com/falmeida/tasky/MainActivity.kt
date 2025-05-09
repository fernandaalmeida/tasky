package com.falmeida.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.falmeida.tasky.ui.navigation.TaskyNavHost
import com.falmeida.tasky.feature.auth.ui.register.RegisterScreenWrapper
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.falmeida.tasky.designsystem.component.theme.TaskyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            TaskyTheme {
                val navController = rememberNavController()

                TaskyNavHost(
                    navController = navController,
                    startDestination = "register" // or "login" if skipping splash screen
                )
            }
        }
    }
}