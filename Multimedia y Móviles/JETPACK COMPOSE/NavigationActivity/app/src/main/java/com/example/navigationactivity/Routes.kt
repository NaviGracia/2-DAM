package com.example.navigationactivity

sealed class Routes(val route: String) {
    object MainScreen : Routes("MainScreen")
    object PlayScreen : Routes("PlayScreen?user={user}") {
        fun createRoute(user: String) = "PlayScreen?user=$user"
    }
    object ResultScreen : Routes("ResultScreen?bet={bet}&tries={tries}") {
        fun createRoute(bet: Int, tries: Int) = "ResultScreen?bet=$bet&tries=$tries"
    }
}