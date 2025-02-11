package com.example.mytodolist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo_list_compose.components.BottomNavigationBar
import com.example.todo_list_compose.navigation.NavGraph
import com.example.todo_list_compose.viewmodel.TaskViewModel

@Composable
fun TodoApp(viewModel: TaskViewModel = viewModel()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("To-Do List") }) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        NavGraph(modifier = Modifier.padding(paddingValues).padding(16.dp), viewModel)
    }
}
