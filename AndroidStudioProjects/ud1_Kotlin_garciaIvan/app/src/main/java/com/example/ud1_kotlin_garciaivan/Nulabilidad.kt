package com.example.ud1_kotlin_garciaivan


    fun main() {
        var name = "David"
        var name2: String? = null //? String nulable
        println(name)
        println(name2)

        if (name == "David") {
        }

        //if (name2.substring(startIndex = 0) == "David") {}
        if (name2 != null) {
            if (name2.substring(startIndex = 2) == "David") {
            }
            println("Entra aquí")
        }

        //if (name2!![0] == 'D') {} // !! Seguro que no es null

        //if (name2!!.substring(1)) {} // !! Seguro que no es null

        if (name2?.get(0) == 'D') {
        } //Si no es null, dame el valor

        println(name2?.get(0) ?: "Es Nulo")
        showText(name)
       //( showText(name2)
    }

    fun showText(text:String):Int? {
        println(text)
        return if (text?.length ?: 0 > 5){
            10
        }else null
    }


