package Ejercicios

fun main() {
    printFinalTemperature(27.0, "Celsius", "Fahrenheit") {
        initialMeasurement -> initialMeasurement*9.0/5.0 + 32.0
    }
    printFinalTemperature(350.0, "Kelvin", "Celsius") {
            initialMeasurement -> initialMeasurement - 273.15
    }
    printFinalTemperature(10.0, "Fahrenheit", "Kelvin") {
            initialMeasurement -> 5.0/9.0 * (initialMeasurement - 32) + 273.15
    }
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}