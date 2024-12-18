package com.example.jetpacktest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun MyLazyLayout(){
    val myList = listOf("Ajani", "Liliana", "Kaito")
    Column (modifier = Modifier.fillMaxWidth()) {
        LazyRow {
            item { Text(text = "Hola") }
            items(myList){
                Text(text = "$it")
            }
        }
        LazyColumn {
            items(7){
                Text(text = "Este es el item $it")
            }
        }
    }
}