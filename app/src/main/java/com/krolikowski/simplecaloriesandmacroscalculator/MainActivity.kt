package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var weight: Int = 0
    var height: Int = 0
    var age = 0
    var sex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun countCalories (wei: Int, hei: Int, ag: Int, se: Int) = (10*wei)+(6.25*hei)-(5*ag) + se
}