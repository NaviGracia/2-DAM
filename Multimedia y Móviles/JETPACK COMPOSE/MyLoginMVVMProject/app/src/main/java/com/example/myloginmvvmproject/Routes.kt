package com.example.myloginmvvmproject

sealed class Routes(val route:String){
    object LoginScreen: Routes("LoginScreen")
    object ScaffoldScreen: Routes("ScaffoldScreen")
    object Screen3: Routes("Screen3?age={age}"){
        fun createRoute(age: Int) = "Screen3?age=$age"
    }
}