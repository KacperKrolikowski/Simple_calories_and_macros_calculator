package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import kotlin.math.roundToInt

class CalculatedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculated)

        supportActionBar!!.hide()

        val calories: Double = intent.getDoubleExtra("value", 1500.0)
        val caloriesInt: Int = calories.roundToInt()

        val weight: Double = intent.getDoubleExtra("weig", 60.0)
        val weightInt: Int = weight.roundToInt()

        val caloriesTV: TextView = findViewById(R.id.calories_TV)
        (getString(R.string.your_bmr)+ " " + caloriesInt.toString()).also { caloriesTV.text = it }

        var mGoalSet = 0

        val mProteinAmount: TextView = findViewById(R.id.protein_amount_tv)
        val mFatAmount: TextView = findViewById(R.id.fat_amount_tv)
        val mCarbsAmount: TextView = findViewById(R.id.carbs_amount_tv)
        (amountOfProtein(weightInt).toString() + "g").also { mProteinAmount.text = it }
        (amountOfFats(caloriesInt).roundToInt().toString() + "g").also { mFatAmount.text = it }
        (amountOfCarbs(caloriesInt, weightInt).roundToInt().toString() + "g").also { mCarbsAmount.text = it }

        val mAfterCaloriesChange: TextView = findViewById(R.id.calories_after_change_tv)
        mAfterCaloriesChange.text = caloriesInt.toString()

        val mGoalInfoTV: TextView = findViewById(R.id.amount_of_change)

        val mGoalSeekBat: SeekBar = findViewById(R.id.goal_seekbar)
        mGoalSeekBat.progress = 6
        mGoalSeekBat.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress){
                    1 -> {
                        mGoalInfoTV.text = getString(R.string.minus_0_5)
                        mGoalSet = -500
                    }
                    2 -> {
                        mGoalInfoTV.text = getString(R.string.minus_0_4)
                        mGoalSet = -400
                    }
                    3 -> {
                        mGoalInfoTV.text = getString(R.string.minus_0_3)
                        mGoalSet = -300
                    }
                    4 -> {
                        mGoalInfoTV.text = getString(R.string.minus_0_2)
                        mGoalSet = -200
                    }
                    5 -> {
                        mGoalInfoTV.text = getString(R.string.minus_0_1)
                        mGoalSet = -100
                    }
                    6 -> {
                        mGoalInfoTV.text = getString(R.string.your_calories_0)
                        mGoalSet = 0
                    }
                    7 -> {
                        mGoalInfoTV.text = getString(R.string.plus_0_1)
                        mGoalSet = 100
                    }
                    8 -> {
                        mGoalInfoTV.text = getString(R.string.plus_0_2)
                        mGoalSet = 200
                    }
                    9 -> {
                        mGoalInfoTV.text = getString(R.string.plus_0_3)
                        mGoalSet = 300
                    }
                    10 -> {
                        mGoalInfoTV.text = getString(R.string.plus_0_4)
                        mGoalSet = 400
                    }
                    11 -> {
                        mGoalInfoTV.text = getString(R.string.plus_0_5)
                        mGoalSet = 500
                    }
                }
                mAfterCaloriesChange.text = (caloriesInt+mGoalSet).toString()
                (amountOfProtein(weightInt).toString() + "g").also { mProteinAmount.text = it }
                (amountOfFats(caloriesInt+mGoalSet).roundToInt().toString() + "g").also { mFatAmount.text = it }
                (amountOfCarbs(caloriesInt+mGoalSet, weightInt).roundToInt().toString() + "g").also { mCarbsAmount.text = it }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        val adView = AdView(this)
        adView.adSize = AdSize.SMART_BANNER

    }


    private fun amountOfProtein(wei: Int) = (wei*2)

    private fun amountOfFats(cal: Int) = (cal*0.3)/9

    private fun amountOfCarbs(cal: Int, wei: Int) = (cal-((amountOfProtein(wei)*4)+(amountOfFats(cal)*9)))/4
}