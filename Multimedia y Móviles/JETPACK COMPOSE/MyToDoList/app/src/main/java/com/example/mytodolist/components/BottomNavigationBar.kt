package com.example.mytodolist.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.List, contentDescription = "All") },
            label = { Text("All") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Icon(Icons.Default.Star, contentDescription = "Favorites") },
            label = { Text("Favorites") }
        )
    }
}
