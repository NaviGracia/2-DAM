package com.example.mytodolist.model

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val dueDate: String,
    val priority: String
)
