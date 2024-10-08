package com.example.ud1_kotlin_ivangarca

fun main() {
    var listaMesas:List<Int> = listOf(1,2,4,3)
    println("Resultados Coste de los Platos:")
    println(calcularCostePlatos(4.4))
    println(calcularCostePlatos(5.6))
    println(calcularCostePlatos(15.0))
    println(calcularCostePlatos(15.5))
    println(calcularCostePlatos(31.6))

    println()

    println("Resultados Gestión de Reservas:")
    println(gestionReservas(1, listaMesas))
    println(gestionReservas(2, listaMesas))
    println(gestionReservas(3, listaMesas))
    println(gestionReservas(2, listaMesas))
    println(gestionReservas(4, listaMesas))

    println()

    println("Resultados Mostrar Reserva:")
    println(mostrarReserva("Tu", "8:50", 2, false))
    println(mostrarReserva("Yo", "9:50", 1, true))
}

fun calcularCostePlatos(coste:Double):String {
    var relacion = "Relación Caro/Barato: "
    return relacion + when(coste){
        in 0.0..5.0 -> "€"
        in 5.5..8.0 -> "€€"
        in 8.5..15.0 -> "€€€"
        in 15.5..21.5 -> "€€€€"
        else -> {"€€€€€"}
    }
}

fun gestionReservas(comensales:Int, listaMesas:List<Int>):Boolean{
    var mesaAdecuada:Boolean = false
    listaMesas.forEach(){
        if (it == comensales){
            mesaAdecuada = true
            listaMesas.drop(it)
        }
    }
    return mesaAdecuada
}

fun mostrarReserva(nombre:String, hora:String, fecha:Int, boolean:Boolean){
    if (boolean == true){
        println("Reserva: " + nombre + ", " + hora + " " + fecha + "Disponibilidad: " + boolean +
                "Desea confirmar la reserva")
    }else{
        println("No hay disponibilidad")
    }
}

/* Sin acabar
fun procesarReserva(comensales:Int, listaMesas:List<Int>, nombre:String, hora:String, fecha:Int, boolean:Boolean){
    gestionReservas()
    mostrarReserva()
}

 */