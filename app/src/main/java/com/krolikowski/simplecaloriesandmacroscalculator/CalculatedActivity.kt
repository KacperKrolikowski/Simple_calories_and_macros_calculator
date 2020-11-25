package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import kotlin.math.roundToInt

class CalculatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculated)

        val calories: Double = intent.getDoubleExtra("value", 1500.0)
        var caloriesInt: Int = calories.roundToInt()

        val caloriesTV: TextView = findViewById(R.id.calories_TV)
        caloriesTV.text = getString(R.string.your_bmr)+ " " + caloriesInt.toString()
    }
}