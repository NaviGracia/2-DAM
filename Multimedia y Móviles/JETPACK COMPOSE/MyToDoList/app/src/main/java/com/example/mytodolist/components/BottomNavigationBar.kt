package com.example.mytodolist.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun BottomNavigationBar() {
    BottomNavigation {
        BottomNavigationItem(selected = true, onClick = {}, icon = { Icon(Icons.Default.List, contentDescription = "All") }, label = { Text("All") })
        BottomNavigationItem(selected = false, onClick = {}, icon = { Icon(Icons.Default.Star, contentDescription = "Favorites") }, label = { Text("Favorites") })
    }
}
