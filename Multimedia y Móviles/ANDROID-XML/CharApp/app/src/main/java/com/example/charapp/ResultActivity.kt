package com.example.charapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    private lateinit var tvCharName: TextView
    private lateinit var tvRace: TextView
    private lateinit var tvPowerNumber: TextView

    private fun initComponents(){
        tvCharName = findViewById(R.id.tvCharName)
        tvRace = findViewById(R.id.tvRace)
        tvPowerNumber = findViewById(R.id.tvPowerNumber)
    }

    private fun initUI(){
        tvCharName.text = intent.extras?.getString("name")
        tvRace.text = intent.extras?.getString("img")
        tvPowerNumber.text = intent.extras?.getString("power")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initUI()
    }
}