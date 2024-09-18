package com.example.ud1_kotlin_garciaivan

fun main(){
    numericas()
    alfanum()
    logicas()
    showMyName("Slim Shady")
    showMyNameAndAge("Slim Shady", 34)
    showMyNameAndAge("Slim Shady")
    println("La resta es: ${substract(10, 5)}")
    println("La multiplicación es: ${multiply(10, 5)}")
    println("La división es: ${div(10, 5)}")
}

fun logicas() {
    //Boolean
    val trueOrFalse:Boolean = true
    val tOrF = false
    println("Primera: $trueOrFalse y Segunda: $tOrF")

    //Operaciones
    var nueva = !tOrF
    nueva = 10 > 5
    nueva = trueOrFalse || tOrF
    nueva = trueOrFalse && tOrF
}

fun alfanum() {
    //Char
    val myChar = 'u'

    //String
    var myString = "Mi nombre es: "
    val name = "Navi"
    println(myString + name)
    var msg = "Mi nombre es: $name"
    println(msg)
    println("Soy: $name")
    println("Soy en mayúsculas: ${name.uppercase()}")
}

fun numericas(){
    //Int
    val age:Int = 38
    val age2 = 38
    var age3 = 38
    //age2 = 40 ESTO NO SE PUEDE
    println(age)
    println(age3)
    age3 = age + age2
    println(age3)

    //LONG
    val superficie:Long = 123456789
    val super2 = 50228372375485

    //DOUBLE & FLOAT
    var volumen = 10.5
    var volumen2 = 10.5f
    var volumen3:Float = 10.5f
    val volumen4:Double = 10.5

}

fun showMyName(name:String){
    println("My name is: $name")
}

fun showMyNameAndAge(name:String, age:Int = 25){
    println("My name is: $name and my age is: $age")
}
fun substract(first:Int, second:Int):Int {
    return first-second
}

fun multiply(first:Int, second:Int):Int = first * second
fun div(first:Int, second:Int) = (first.toDouble() / second)