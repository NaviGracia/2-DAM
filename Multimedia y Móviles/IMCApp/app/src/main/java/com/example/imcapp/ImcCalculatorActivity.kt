package com.example.imcapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var rsHeight:Slider
    private lateinit var tvHeight:TextView
    private var cardSelected:Boolean = false

    private fun initComponents(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        rsHeight = findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
    }

    private fun initListeners(){
        viewMale.setOnClickListener{
            cardSelected = true
            setGenderColor()
            cardSelected = false
        }
        viewFemale.setOnClickListener{
            cardSelected = true
            setGenderColor()
            cardSelected = false
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            tvHeight.text = DecimalFormat("#.##").format(value) + "cm"
        }
    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(cardSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(cardSelected))
    }

    private fun getBackgroundColor(isComponentSelected: Boolean):Int{
        val colorReference = if(isComponentSelected){
            R.color.bg_comp_sel
        }else{R.color.bg_comp}
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI(){
        setGenderColor()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
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