package com.example.charapp

import android.widget.EditText
import androidx.cardview.widget.CardView
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    private var raceSelected: Boolean = false
    private var imgRaceSelected: String = ""
    private lateinit var name: String
    private lateinit var etName: EditText
    private lateinit var slYuanti: CardView
    private lateinit var imgYuanti: ImageView
    private lateinit var slOrc: CardView
    private lateinit var imgOrc: ImageView
    private lateinit var slTiefling: CardView
    private lateinit var imgTiefling: ImageView
    private lateinit var tvPower: TextView
    private lateinit var rsPower: Slider
    private lateinit var btnCreate: FloatingActionButton

    private fun initComponents(){
        etName = findViewById(R.id.etName)
        slYuanti = findViewById(R.id.slYuanti)
        imgYuanti = findViewById(R.id.imgYuanti)
        slOrc = findViewById(R.id.slOrc)
        imgOrc = findViewById(R.id.imgOrc)
        slTiefling = findViewById(R.id.slTiefling)
        imgTiefling = findViewById(R.id.imgTiefling)
        tvPower = findViewById(R.id.tvPower)
        rsPower = findViewById(R.id.rsPower)
        btnCreate = findViewById(R.id.btnCreate)
    }

    private fun initListeners(){
        slYuanti.setOnClickListener {
            raceSelected==true
            imgRaceSelected = "yuanti"
            slYuanti.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selected))
            slOrc.setCardBackgroundColor(ContextCompat.getColor(this, R.color.noSelected))
            slTiefling.setCardBackgroundColor(ContextCompat.getColor(this, R.color.noSelected))
        }

        slOrc.setOnClickListener {
            raceSelected=true
            imgRaceSelected = "orc"
            slOrc.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selected))
            slYuanti.setCardBackgroundColor(ContextCompat.getColor(this, R.color.noSelected))
            slTiefling.setCardBackgroundColor(ContextCompat.getColor(this, R.color.noSelected))
        }

        slTiefling.setOnClickListener {
            raceSelected=true
            imgRaceSelected = "tiefling"
            slTiefling.setCardBackgroundColor(ContextCompat.getColor(this, R.color.selected))
            slYuanti.setCardBackgroundColor(ContextCompat.getColor(this, R.color.noSelected))
            slOrc.setCardBackgroundColor(ContextCompat.getColor(this, R.color.noSelected))
        }

        rsPower.addOnChangeListener { _, value, _ ->
            tvPower.text = DecimalFormat("#.##").format(value)
        }

        btnCreate.setOnClickListener {
            createUser()
        }
    }

    private fun createUser(){
        name = etName.text.toString()
        val intentGA = Intent(this, ResultActivity::class.java)
        intentGA.putExtra("name", name)
        intentGA.putExtra("img", imgRaceSelected)
        intentGA.putExtra("power", tvPower.text.toString())
        startActivity(intentGA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
}