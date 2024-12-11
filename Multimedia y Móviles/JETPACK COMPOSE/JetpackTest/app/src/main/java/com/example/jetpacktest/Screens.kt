package com.example.jetpacktest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navigationController: NavHostController){
    Column (modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "MainScreen")
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red))
        Button(onClick = {navigationController.navigate(Routes.Screen2.route)}) {
            Text(text = "Ir a Screen2")
        }
    }
}

@Composable
fun Screen2(navigationController: NavHostController){
    Column (modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "Screen2")
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red))
        Button(onClick = {navigationController.navigate(Routes.Screen3.route)}) {
            Text(text = "Ir a Screen3")
        }
    }
}

@Composable
fun Screen3(navigationController: NavHostController){
    Column (modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "Screen3")
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red))
        Button(onClick = {navigationController.navigate(Routes.Screen4.createRoute("Hola")) }) {
            Text(text = "Ir a Screen4")
        }
    }
}

@Composable
fun Screen4(navigationController: NavHostController){
    Column (modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "Screen4")
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red))
        Button(onClick = {navigationController.navigate(Routes.Screen5.createRoute(38))}) {
            Text(text = "Ir a Screen5")
        }
    }
}

@Composable
fun Screen5(navigationController: NavHostController, age: Int){
    Column (modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "Edad = $age")
        Box(modifier = Modifier
            .size(100.dp)
            .background(color = Color.Red))
        Button(onClick = {navigationController.navigate(Routes.MainScreen.route)}) {
            Text(text = "Ir a MainScreen")
        }
    }
}