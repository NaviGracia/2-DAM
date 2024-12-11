package com.example.jetpacktest

sealed class Routes(val route:String){
    object MainScreen: Routes("MainScreen")
    object Screen2: Routes("Screen2")
    object Screen3: Routes("Screen3")
    object Screen4: Routes("Screen4/{name}"){
        fun createRoute(name: String) = "Screen4/$name"
    }
    object Screen5: Routes("Screen5?age={age}"){
        fun createRoute(age: Int) = "Screen5?age=$age"
    }
}