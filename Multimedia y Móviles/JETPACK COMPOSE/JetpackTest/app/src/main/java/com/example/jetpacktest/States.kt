package com.example.jetpacktest

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun SimpleFilledTextFieldSample(){
    var text by remember { mutableStateOf("Hello") }

    TextField(
        value = text,
        onValueChange = {text = it},
        label = {Text("label")}
        )
}