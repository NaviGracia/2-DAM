package com.example.navegationapp

sealed class Routes(val route:String){
    object MainScreen: Routes("MainScreen")
    object Screen2: Routes("Screen2/{name}"){
        fun createRoute(name: String) = "Screen2/$name"
    }
    object Screen3: Routes("Screen3?age={age}"){
        fun createRoute(age: Int) = "Screen3?age=$age"
    }
}