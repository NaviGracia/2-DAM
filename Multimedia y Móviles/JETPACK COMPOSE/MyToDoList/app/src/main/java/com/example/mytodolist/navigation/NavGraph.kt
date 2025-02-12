package com.example.mytodolist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytodolist.ui.TaskScreen
import com.example.mytodolist.viewmodel.TaskViewModel


@Composable
fun NavGraph(modifier: Modifier, viewModel: TaskViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "tasks") {
        composable("tasks") { TaskScreen(viewModel) }
    }
}
