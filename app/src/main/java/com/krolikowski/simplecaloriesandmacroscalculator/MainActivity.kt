package com.krolikowski.simplecaloriesandmacroscalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
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


        val mActivityInformation: TextView = findViewById(R.id.activityLevelInfo_tv)

        val mActivityPicker: SeekBar = findViewById(R.id.activityPicker_seekbat)
        mActivityPicker.setProgress(3, true)

        mActivityPicker.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress){
                    1 -> {
                        mActivityInformation.text = "Mostly inactive or sedentary lifestyle"
                    }
                    2 -> {
                        mActivityInformation.text = "Quite active (walking or exercising 1-2 times a week)"
                    }
                    3 -> {
                        mActivityInformation.text = "Moderately active (exercising 2-3 times a week)"
                    }
                    4 -> {
                        mActivityInformation.text = "Active (heavy exercise 3-4 times a week)"
                    }
                    5 -> {
                        mActivityInformation.text = "Very active (heavy exercise 6 times a week)"
                    }
                    else -> {
                        mActivityInformation.text = " "
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


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