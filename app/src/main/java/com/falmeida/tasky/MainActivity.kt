package com.falmeida.tasky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.falmeida.tasky.core.navigation.Screen
import com.falmeida.tasky.designsystem.component.theme.TaskyTheme
import com.falmeida.tasky.ui.navigation.TaskyNavHost
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
                    startDestination = Screen.Login.route ,
                )
            }
        }
    }
}