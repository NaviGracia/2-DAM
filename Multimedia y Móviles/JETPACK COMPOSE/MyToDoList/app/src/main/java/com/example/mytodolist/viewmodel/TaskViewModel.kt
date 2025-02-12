package com.example.mytodolist.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.mytodolist.model.Task

class TaskViewModel : ViewModel() {
    var tasks: List<Task> by mutableStateOf(emptyList())
        private set

    fun addTask(name: String, description: String, priority: String, dueDate: String) {
        val newTask = Task(
            id = if (tasks.isEmpty()) 1 else tasks.maxOf { it.id } + 1,
            name = name,
            description = description,
            dueDate = dueDate,
            priority = priority
        )
        tasks = tasks + newTask
    }

    fun deleteTask(taskId: Int) {
        tasks = tasks.filter { it.id != taskId }
    }
}
