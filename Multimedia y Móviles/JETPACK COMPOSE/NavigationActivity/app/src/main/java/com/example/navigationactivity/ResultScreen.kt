package com.example.navigationactivity

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.random.Random

@Composable
fun ResultScreen(navigationController: NavHostController, bet: Int, tries: Int) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Body(
            Modifier.align(Alignment.Center),
            navigationController,
            bet,
            tries
        )
    }
}

@Composable
private fun Body(modifier: Modifier, navigationController: NavHostController, bet: Int, tries: Int) {
    ResultText(bet, tries)
}

@Composable
fun ResultText(bet: Int, tries: Int) {
    val randomNumber = remember { Random.nextInt(1, 11) }
    val resultMessage = if (bet == randomNumber) {
        "¡Felicidades! Has acertado el número $randomNumber en el intento $tries."
    } else {
        "Lo siento, no has acertado. El número era $randomNumber."
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = resultMessage,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}