package com.example.navigationactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationactivity.ui.theme.NavigationActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationActivityTheme {
                val navigationController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.MainScreen.route
                    ) {
                        composable(Routes.MainScreen.route) { MainScreen(navigationController) }
                        composable(Routes.PlayScreen.route) { backStackEntry ->
                            val user = backStackEntry.arguments?.getString("user") ?: "Guest"
                            PlayScreen(navigationController, user)
                        }
                        composable(Routes.ResultScreen.route) { backStackEntry ->
                            val bet = backStackEntry.arguments?.getString("bet")?.toInt() ?: 0
                            val tries = backStackEntry.arguments?.getString("tries")?.toInt() ?: 0
                            ResultScreen(navigationController, bet, tries)
                        }
                    }
                }
            }
        }
    }
}