package com.example.mytodolist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytodolist.components.BottomNavigationBar
import com.example.mytodolist.navigation.NavGraph
import com.example.mytodolist.viewmodel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(viewModel: TaskViewModel = viewModel()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("To-Do List") }) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        NavGraph(modifier = Modifier.padding(paddingValues).padding(16.dp), viewModel)
    }
}
