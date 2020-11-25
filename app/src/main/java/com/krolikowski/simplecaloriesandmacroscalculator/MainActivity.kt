package com.krolikowski.simplecaloriesandmacroscalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    var weight: Int = 0
    var height: Int = 0
    var age = 0
    var sex = 0
    var activityLevel: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mWeightPicker: EditText = findViewById(R.id.weight_picker)
        mWeightPicker.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weight = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }

        )


        val mHeightPicker: EditText = findViewById(R.id.height_picker)
        mHeightPicker.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                height = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }

        )


        val mAgePicker: EditText = findViewById(R.id.age_picker)
        mAgePicker.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                age = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        val mActivityInformation: TextView = findViewById(R.id.activityLevelInfo_tv)

        val mActivityPicker: SeekBar = findViewById(R.id.activityPicker_seekbat)
        mActivityPicker.setProgress(3, true)

        mActivityPicker.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress){
                    1 -> {
                        mActivityInformation.text = "Mostly inactive or sedentary lifestyle"
                        activityLevel = 1.2
                    }
                    2 -> {
                        mActivityInformation.text = "Quite active (walking or exercising 1-2 times a week)"
                        activityLevel = 1.3
                    }
                    3 -> {
                        mActivityInformation.text = "Moderately active (exercising 2-3 times a week)"
                        activityLevel = 1.4
                    }
                    4 -> {
                        mActivityInformation.text = "Active (heavy exercise 3-4 times a week)"
                        activityLevel = 1.5
                    }
                    5 -> {
                        mActivityInformation.text = "Very active (heavy exercise 5-6 times a week)"
                        activityLevel = 1.7
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

        val mSexRadioGroup: RadioGroup = findViewById(R.id.sexGroup)
        mSexRadioGroup.setOnCheckedChangeListener { group, checkedId -> run{
            var RB: RadioButton = findViewById(checkedId)
            sex = if(RB.text == "Male"){
                5
            } else{
                -161
            }
        } }
        


        val mCalcButton = findViewById<Button>(R.id.calculate_button)
        mCalcButton.setOnClickListener {

            var caloriesValue = countCalories(weight, height, age, sex, activityLevel)
            val intent = Intent(applicationContext, CalculatedActivity::class.java)
            intent.putExtra("value", caloriesValue)
            startActivity(intent)

        }



    }

    private fun countCalories (wei: Int, hei: Int, ag: Int, se: Int, activ: Double) = ((10*wei)+(6.25*hei)-(5*ag) + se)*activ
}