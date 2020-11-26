package com.krolikowski.simplecaloriesandmacroscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.math.roundToInt

class CalculatedActivity : AppCompatActivity() {

    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculated)

        val calories: Double = intent.getDoubleExtra("value", 1500.0)
        var caloriesInt: Int = calories.roundToInt()

        val weight: Double = intent.getDoubleExtra("weig", 60.0)
        var weightInt: Int = weight.roundToInt()

        val caloriesTV: TextView = findViewById(R.id.calories_TV)
        caloriesTV.text = getString(R.string.your_bmr)+ " " + caloriesInt.toString()

        var mGoalSet: Int = 0

        val mProteinAmount: TextView = findViewById(R.id.protein_amount_tv)
        val mFatAmount: TextView = findViewById(R.id.fat_amount_tv)
        val mCarbsAmount: TextView = findViewById(R.id.carbs_amount_tv)
        mProteinAmount.text = amountOfProtein(weightInt).toString() + "g"
        mFatAmount.text = amountOfFats(caloriesInt).roundToInt().toString() + "g"
        mCarbsAmount.text = amountOfCarbs(caloriesInt, weightInt).roundToInt().toString() + "g"

        val mAfterCaloriesChange: TextView = findViewById(R.id.calories_after_change_tv)
        mAfterCaloriesChange.text = caloriesInt.toString()

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
                mProteinAmount.text = amountOfProtein(weightInt).toString() + "g"
                mFatAmount.text = amountOfFats(caloriesInt+mGoalSet).roundToInt().toString() + "g"
                mCarbsAmount.text = amountOfCarbs(caloriesInt+mGoalSet, weightInt).roundToInt().toString() + "g"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        MobileAds.initialize(this){}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }


    private fun amountOfProtein(wei: Int) = (wei*2)

    private fun amountOfFats(cal: Int) = (cal*0.3)/9

    private fun amountOfCarbs(cal: Int, wei: Int) = (cal-((amountOfProtein(wei)*4)+(amountOfFats(cal)*9)))/4
}