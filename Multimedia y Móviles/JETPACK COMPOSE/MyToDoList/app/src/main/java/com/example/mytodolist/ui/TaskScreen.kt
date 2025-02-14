package com.example.mytodolist.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytodolist.components.TaskCard
import com.example.mytodolist.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TaskScreen(viewModel: TaskViewModel = viewModel()) {
    var taskName by remember { mutableStateOf(TextFieldValue()) }
    var taskDescription by remember { mutableStateOf(TextFieldValue()) }
    var taskPriority by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "To-Do List", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("Task Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = taskPriority,
            onValueChange = { taskPriority = it },
            label = { Text("Priority (Alta, Media, Baja)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                if (taskName.text.isBlank() || taskDescription.text.isBlank() || taskPriority.text.isBlank()) {
                    errorMessage = "Todos los campos son obligatorios"
                } else {
                    errorMessage = null
                    val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                    viewModel.addTask(taskName.text, taskDescription.text, taskPriority.text, currentDate)

                    taskName = TextFieldValue("")
                    taskDescription = TextFieldValue("")
                    taskPriority = TextFieldValue("")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(viewModel.tasks) { task ->
                TaskCard(task = task, onDelete = { viewModel.deleteTask(task.id) })
            }
        }
    }
}
