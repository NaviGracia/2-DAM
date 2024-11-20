package com.example.jetpacktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import com.example.jetpacktest.ui.theme.JetpackTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTestTheme {
                MyFirstApp()
            }
        }
    }
}

//FirstApp
@Preview(showBackground = true)
@Composable
fun MyFirstApp() {
    JetpackTestTheme {
        FirstComplexLayout()
    }
}

@Composable
fun FirstComplexLayout(){
    Column(modifier = Modifier.fillMaxSize()) {
        createText("TITULO", 20, Modifier.fillMaxWidth())
        createText("Subtítulo ", 10, Modifier.fillMaxWidth())
        Row(modifier = Modifier.fillMaxWidth()){
            createText("Opción 1", 5, Modifier.weight(1f))
            createText("Opción 2", 5, Modifier.weight(1f))
            createText("Opción 3 ", 5, Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()){
            createText("Texto 1", 5, Modifier.weight(1f))
            createText("Texto 2", 5, Modifier.weight(1f))
            createText("Texto 3", 5, Modifier.weight(1f))
        }
    }
}

@Composable
fun createText(newText: String, size: Int, newModifier: Modifier){
    Text(
        text = newText,
        textAlign = TextAlign.Center,
        fontSize = size.em,
        modifier = newModifier
    )
}