package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.SeekBar
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

        var mGoalSet: Int = 0
        val mAfterCaloriesChange: TextView = findViewById(R.id.calories_after_change_tv)
        val mGoalInfoTV: TextView = findViewById(R.id.amount_of_change)
        val mGoalSeekBat: SeekBar = findViewById(R.id.goal_seekbar)
        mGoalSeekBat.progress = 6
        mGoalSeekBat.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress){
                    1 -> {
                        mGoalInfoTV.text = "-0,5 kg/week"
                        mGoalSet = -500
                    }
                    2 -> {
                        mGoalInfoTV.text = "-0,4 kg/week"
                        mGoalSet = -400
                    }
                    3 -> {
                        mGoalInfoTV.text = "-0,3 kg/week"
                        mGoalSet = -300
                    }
                    4 -> {
                        mGoalInfoTV.text = "-0,2 kg/week"
                        mGoalSet = -200
                    }
                    5 -> {
                        mGoalInfoTV.text = "-0,1 kg/week"
                        mGoalSet = -100
                    }
                    6 -> {
                        mGoalInfoTV.text = getString(R.string.your_calories_0)
                        mGoalSet = 0
                    }
                    7 -> {
                        mGoalInfoTV.text = "+0,1 kg/week"
                        mGoalSet = 100
                    }
                    8 -> {
                        mGoalInfoTV.text = "+0,2 kg/week"
                        mGoalSet = 200
                    }
                    9 -> {
                        mGoalInfoTV.text = "+0,3 kg/week"
                        mGoalSet = 300
                    }
                    10 -> {
                        mGoalInfoTV.text = "+0,4 kg/week"
                        mGoalSet = 400
                    }
                    11 -> {
                        mGoalInfoTV.text = "+0,5 kg/week"
                        mGoalSet = 500
                    }
                }
                mAfterCaloriesChange.text = (caloriesInt+mGoalSet).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })



    }
}