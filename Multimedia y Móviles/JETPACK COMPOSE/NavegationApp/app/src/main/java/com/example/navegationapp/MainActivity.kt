package com.example.navegationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegationapp.ui.theme.NavegationAppTheme
import com.example.navegationapp.Routes.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegationAppTheme {
                val navigationController = rememberNavController()
                NavHost(navController = navigationController,
                    startDestination = Routes.MainScreen.route){
                    composable(Routes.MainScreen.route) { MainScreen(navigationController) }
                    composable(
                        Screen2(navigationController, it.arguments?.getString("name").orEmpty())
                    composable(
                        Routes.Screen5.route,
                        arguments = listOf(navArgument("age"){defaultValue = 25})
                    ) {
                        Routes.Screen5(
                            navigationController,
                            it.arguments?.getInt("age").orEmpty()
                        )
                        }
                }
                MyFirstApp()
            }
        }
    }
}