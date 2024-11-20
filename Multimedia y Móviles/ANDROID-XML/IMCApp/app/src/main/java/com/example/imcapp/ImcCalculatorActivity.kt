package com.example.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var rsHeight:Slider
    private lateinit var tvHeight:TextView
    private lateinit var tvWeight:TextView
    private lateinit var tvAge:TextView
    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var btnMinusAge:FloatingActionButton
    private lateinit var btnPlusWeight:FloatingActionButton
    private lateinit var btnMinusWeight:FloatingActionButton
    private lateinit var btnCal:AppCompatButton
    private var cardSelected:Boolean = false

    private var age:Int = 0
    private var weight:Int = 0

    private fun initComponents(){
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        rsHeight = findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnMinusAge = findViewById(R.id.btnMinusAge)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnMinusWeight = findViewById(R.id.btnMinusWeight)
        btnCal = findViewById(R.id.btnCal)
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

        btnPlusAge.setOnClickListener{
            age+=1
            setAge()
        }

        btnMinusAge.setOnClickListener {
            age-=1
            setAge()
        }

        btnPlusWeight.setOnClickListener{
            weight+=1
            setWeight()
        }

        btnMinusWeight.setOnClickListener {
            weight-=1
            setWeight()
        }

        btnCal.setOnClickListener {
            navigate2result(calculateIMC())
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
        setWeight()
        setAge()
    }

    private fun setWeight(){
        tvWeight.text = weight.toString()
    }

    private fun setAge(){
        tvAge.text = age.toString()
    }

    private fun calculateIMC():Double{
        var altura:Double = tvHeight.text.toString().toDouble()
        return weight / (altura*altura)
    }

    private fun navigate2result(num:Double){
        val intentGA = Intent(this, ImcResultActivity::class.java)
        intentGA.putExtra("Imc", num)
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