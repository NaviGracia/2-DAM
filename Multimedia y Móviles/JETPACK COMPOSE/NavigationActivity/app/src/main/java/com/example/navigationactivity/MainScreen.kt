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
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navigationController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Body(
            Modifier.align(Alignment.Center),
            navigationController
        )
    }
}

@Composable
private fun Body(modifier: Modifier, navigationController: NavHostController) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorMessage by rememberSaveable { mutableStateOf("") }

    val users = listOf(
        "user1" to "password1",
        "user2" to "password2",
        "user3" to "password3",
        "user4" to "password4",
        "user5" to "password5"
    )

    Column(modifier = modifier) {
        User(user) { user = it }
        Spacer(modifier = Modifier.size(9.dp))
        Password(password) { password = it }
        Spacer(modifier = Modifier.size(9.dp))
        LoginButton(navigationController, user, password, users, { errorMessage = it })
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        GuestButton(navigationController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(email: String, function: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { function(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        placeholder = { Text(text = "User") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, function: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { function(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp),
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text(text = "Password") }
    )
}

@Composable
fun LoginButton(
    navigationController: NavHostController,
    user: String,
    password: String,
    users: List<Pair<String, String>>,
    onError: (String) -> Unit
) {
    Button(
        onClick = {
            val userExists = users.any { it.first == user && it.second == password }
            if (userExists) {
                navigationController.navigate(Routes.PlayScreen.createRoute(user))
            } else {
                onError("Usuario o contraseña incorrectos")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContentColor = Color.White,
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun GuestButton(navigationController: NavHostController) {
    Button(
        onClick = {
            navigationController.navigate(Routes.PlayScreen.createRoute("Guest"))
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color(0xFF4EA8E9),
            disabledContentColor = Color.White,
            disabledContainerColor = Color(0xFF78C8F9)
        )
    ) {
        Text(text = "Guest")
    }
}