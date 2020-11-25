package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView

class CalculatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculated)

        val calories: Double = intent.getDoubleExtra("value", 1500.0)

        var mTextViwe: TextView = findViewById(R.id.textView)
        mTextViwe.set

    }
}