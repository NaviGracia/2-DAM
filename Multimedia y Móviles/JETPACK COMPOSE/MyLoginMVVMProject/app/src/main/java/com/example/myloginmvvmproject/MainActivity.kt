package com.example.myloginmvvmproject

import com.example.myloginmvvmproject.Routes.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myloginmvvmproject.login.ui.LoginScreen
import com.example.myloginmvvmproject.login.ui.LoginViewModel
import com.example.myloginmvvmproject.ui.theme.MyLoginMVVMProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLoginMVVMProjectTheme {
                val navigationController = rememberNavController()
                NavHost(navController = navigationController,
                    startDestination = LoginScreen.route){
                    composable(LoginScreen.route) { LoginScreen(navigationController) }
                    composable(
                        Screen2(navigationController, it.arguments?.getString("name").orEmpty())
                LoginScreen(LoginViewModel())
            }
        }
    }
}