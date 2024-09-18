package com.example.ud1_kotlin_garciaivan

fun main(){
    val weekDays = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

    println(weekDays.size)

    for (pos in weekDays.indices){
        println(pos)
        println(weekDays[pos])
    }

    for ((pos, value) in weekDays.withIndex()){
        println("$pos: $value")
    }

    for (day in weekDays){print("$day | ")}
}