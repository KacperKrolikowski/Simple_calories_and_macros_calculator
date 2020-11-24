package com.krolikowski.simplecaloriesandmacroscalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    var weight: Int = 0
    var height: Int = 0
    var age = 0
    var sex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mWeightPicker: EditText = findViewById(R.id.weight_picker)
        mWeightPicker.addTextChangedListener(

        )


        val mHeightPicker: EditText = findViewById(R.id.height_picker)


        val mAgePicker: EditText = findViewById(R.id.age_picker)


        val mActivityPicker: SeekBar = findViewById(R.id.activityPicker_seekbat)
        mActivityPicker.


        val mCalcButton = findViewById<Button>(R.id.calculate_button)
        mCalcButton.setOnClickListener {

            var caloriesValue = countCalories(weight, height, age, sex)
            val intent = Intent(applicationContext, CalculatedActivity::class.java)
            intent.putExtra("value", caloriesValue)
            startActivity(intent)

        }



    }

    fun countCalories (wei: Int, hei: Int, ag: Int, se: Int) = (10*wei)+(6.25*hei)-(5*ag) + se
}