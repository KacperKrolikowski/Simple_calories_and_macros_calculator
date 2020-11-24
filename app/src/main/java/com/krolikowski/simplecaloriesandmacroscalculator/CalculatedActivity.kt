package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

class CalculatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculated)

       val calories: Int = intent.getIntExtra("value", 1500)


    }
}