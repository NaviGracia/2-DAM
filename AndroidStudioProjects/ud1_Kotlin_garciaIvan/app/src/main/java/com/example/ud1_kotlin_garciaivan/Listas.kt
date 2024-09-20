package com.example.ud1_kotlin_garciaivan

fun main(){
    mutableList()
    inmutableList()
}

fun mutableList(){
    val weekDays:MutableList<String> = mutableListOf("Lunes", "Martes", "Miércoles")
    weekDays.add("Viernes")
    weekDays.add(3, "Jueves")
    weekDays.add("Semana")
    weekDays.remove("Semana")
    println(weekDays)

    weekDays.isNotEmpty()
}

fun inmutableList(){
    val readOnlyList:List<String> = listOf("PMDM", "ADA", "DIN", "SGE", "INT", "EIE", "PSP", "PDAM")
    println("PRINT CON .toString()")
    println(readOnlyList.toString())

    println("FOR EACH BÁSICO")
   for (modulo in readOnlyList){
        println(modulo)
    }

    println("PRINT CON FOR EACH LAMBDA (usando nombre predeterminado)")
    readOnlyList.forEach {modulo ->
        println(modulo)
    }

    println("PRINT CON FOR EACH LAMBDA (asignando nombre a la variable)")
    readOnlyList.forEach {
        println(it)
    }

    var listaFiltrada= readOnlyList.filter {modulo ->
        modulo.contains("p", true)
    }

    println("PRINT A LISTA FILTRADA (MÓDULOS QUE TENGAN LA P) CON FOR EACH LAMBDA (asignando nombre a la variable)")
    listaFiltrada.forEach {modulo ->
        println(modulo)
    }
}