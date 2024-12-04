package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcResultActivity : AppCompatActivity() {
    private lateinit var textRs:TextView
    private lateinit var imcNumber:TextView
    private lateinit var summaryRs:TextView
    private lateinit var btnReCal:AppCompatButton

    public fun initComponents(){
        textRs = findViewById(R.id.textRs)
        imcNumber = findViewById(R.id.imcNumber)
        summaryRs = findViewById(R.id.summaryRs)
        btnReCal = findViewById(R.id.btnReCal)
    }

    public fun initListeners(){
        btnReCal.setOnClickListener {
            val intentIMCMain = Intent(this, ImcCalculatorActivity::class.java)
        }
    }

    public fun initUI(){
        setTextResult()
    }

    private fun setTextResult(){
        val imc:Double = intent.extras?.getDouble("Imc")!!
        when(imc){
            in 0.0..18.5 -> textRs.text = "Peso inferior al normal"
            in 18.5..24.9 -> textRs.text = "Peso inferior al normal"
            in 25.0..29.9 -> textRs.text = "Peso inferior al normal"
            else -> "Obesidad"
        }
    }

    private fun setImcResult(){

        val imc:Double = intent.extras?.getDouble("Imc")!!
        imcNumber.text = imc.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }
}