package com.example.imcapp

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
            intentIMCMain.putExtra("EXTRA_NAME", name)
        }
    }

    public fun initUI(){}




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