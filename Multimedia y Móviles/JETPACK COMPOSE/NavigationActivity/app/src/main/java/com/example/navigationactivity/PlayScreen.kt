package com.example.navigationactivity

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PlayScreen(navigationController: NavHostController, user: String) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Body(
            Modifier.align(Alignment.Center),
            navigationController,
            user
        )
    }
}

@Composable
private fun Body(modifier: Modifier, navigationController: NavHostController, user: String) {
    var tries by rememberSaveable { mutableStateOf("") }
    var sliderValue by rememberSaveable { mutableStateOf(1f) }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        Greeting(user)
        TriesField(tries) { tries = it }
        Spacer(modifier = Modifier.size(16.dp))
        NumberSlider(sliderValue) { sliderValue = it }
        Spacer(modifier = Modifier.size(16.dp))
        PlayButton(navigationController, tries, sliderValue.toInt()) { errorMessage = it }
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun Greeting(user: String) {
    val greetingText = if (user == "Guest") "Hola, Invitado!" else "Hola, $user!"
    Text(
        text = greetingText,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriesField(tries: String, function: (String) -> Unit) {
    TextField(
        value = tries,
        onValueChange = { function(it) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = { Text(text = "Número de intentos") }
    )
}

@Composable
fun NumberSlider(value: Float, onValueChange: (Float) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Selecciona un número: ${value.toInt()}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 1f..10f,
            steps = 9,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun PlayButton(navigationController: NavHostController, tries: String, bet: Int, onError: (String) -> Unit) {
    val isTriesValid = tries.isNotEmpty() && tries.toIntOrNull() != null

    Button(
        onClick = {
            if (isTriesValid) {
                navigationController.navigate(Routes.ResultScreen.createRoute(bet, tries.toInt()))
            } else {
                onError("Por favor, ingrese un número válido de intentos")
            }
        },
        enabled = isTriesValid,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContentColor = Color.White,
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ) {
        Text(text = "Jugar")
    }
}