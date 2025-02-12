package com.example.mytodolist.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mytodolist.model.Task

@Composable
fun TaskCard(task: Task, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${task.name}", style = MaterialTheme.typography.headlineSmall)
            Text("Description: ${task.description}")
            Text("Due Date: ${task.dueDate}")
            Text("Priority: ${task.priority}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Delete", color = Color.White)
            }
        }
    }
}
