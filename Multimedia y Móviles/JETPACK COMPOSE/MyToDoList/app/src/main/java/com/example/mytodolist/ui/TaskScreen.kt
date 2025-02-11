package com.example.mytodolist.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.mytodolist.model.Task
import java.text.SimpleDateFormat
import java.util.*

class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf(generateInitialTasks())
        private set

    fun addTask(name: String, description: String, priority: String) {
        val newTask = Task(
            id = tasks.size + 1,
            name = name,
            description = description,
            dueDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
            priority = priority
        )
        tasks = tasks + newTask
    }

    fun deleteTask(taskId: Int) {
        tasks = tasks.filter { it.id != taskId }
    }

    companion object {
        fun generateInitialTasks(): List<Task> {
            return listOf(
                Task(1, "Comprar comida", "Comprar frutas y verduras", "12/02/2025", "Alta"),
                Task(2, "Estudiar Kotlin", "Repasar arquitectura MVVM", "15/02/2025", "Media"),
                Task(3, "Hacer ejercicio", "Correr 5km", "10/02/2025", "Baja")
            )
        }
    }
}

