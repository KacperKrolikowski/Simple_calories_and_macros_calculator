package com.krolikowski.simplecaloriesandmacroscalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    var weight: Double = 0.0
    var height: Int = 0
    var heightInch: Double = 0.0
    var age = 0
    var sex = 0
    var activityLevel: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        val mWeightPicker: EditText = findViewById(R.id.weight_picker)
        mWeightPicker.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                weight = try {
                    s.toString().toDouble()
                } catch (e: NumberFormatException){
                    0.0
                }
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
                height = try {
                    s.toString().toInt()
                } catch (e: NumberFormatException){
                    0
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        val mHeightInchPicker: EditText = findViewById(R.id.height_picker_inch)
        mHeightInchPicker.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                heightInch = try {
                    s.toString().toDouble()
                } catch (e: NumberFormatException){
                    0.0
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        val mAgePicker: EditText = findViewById(R.id.age_picker)
        mAgePicker.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                age = try {
                    s.toString().toInt()
                }catch (e: NumberFormatException){
                    0
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        val mActivityInformation: TextView = findViewById(R.id.activityLevelInfo_tv)

        val mActivityPicker: SeekBar = findViewById(R.id.activityPicker_seekbat)
        mActivityPicker.setProgress(1, true)
        mActivityInformation.text = getString(R.string.activity_level_1)

        mActivityPicker.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when(progress){
                    1 -> {
                        mActivityInformation.text = getString(R.string.activity_level_1)
                        activityLevel = 1.2
                    }
                    2 -> {
                        mActivityInformation.text = getString(R.string.activity_level_2)
                        activityLevel = 1.3
                    }
                    3 -> {
                        mActivityInformation.text = getString(R.string.activity_level_3)
                        activityLevel = 1.4
                    }
                    4 -> {
                        mActivityInformation.text = getString(R.string.activity_level_4)
                        activityLevel = 1.5
                    }
                    5 -> {
                        mActivityInformation.text = getString(R.string.activity_level_5)
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
        mSexRadioGroup.setOnCheckedChangeListener { _, checkedId -> run{
            val radioButton: RadioButton = findViewById(checkedId)
            sex = if(radioButton.text == "Male"){
                5
            } else{
                -161
            }
        } }

        val mWeightTV: TextView = findViewById(R.id.weight_kg_tv)
        val mHeightTV: TextView = findViewById(R.id.height_cm_tv)
        val mHeightInchTV: TextView = findViewById(R.id.height_inch_tv)

        val mUnitSwitch: SwitchCompat = findViewById(R.id.unit_switch)
        mUnitSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                mWeightTV.text = getString(R.string.lbs)
                mHeightTV.text = getString(R.string.ft)
                mHeightInchPicker.visibility = View.VISIBLE
                mHeightInchTV.visibility = View.VISIBLE

            } else{
                mWeightTV.text = getString(R.string.kg)
                mHeightTV.text = getString(R.string.cm)
                mHeightInchPicker.visibility = View.INVISIBLE
                mHeightInchTV.visibility = View.INVISIBLE

            }
        }


        val mCalcButton = findViewById<Button>(R.id.calculate_button)
        mCalcButton.setBackgroundColor(getColor(R.color.crimson_red))
        mCalcButton.setOnClickListener {

            if(weight == 0.0 || height == 0 || age == 0 || sex == 0){
                Toast.makeText(this, getString(R.string.incomplete_information), Toast.LENGTH_SHORT).show()
            }else if (!mUnitSwitch.isChecked) {
                val caloriesValue = countCalories(weight, height, age, sex, activityLevel)
                val intent = Intent(applicationContext, CalculatedActivity::class.java)
                intent.putExtra("value", caloriesValue)
                intent.putExtra("weig", weight)
                startActivity(intent)
            }else {
                val caloriesValue = countCaloriesUS(weight, height, heightInch, age, sex, activityLevel)
                val intent = Intent(applicationContext, CalculatedActivity::class.java)
                intent.putExtra("value", caloriesValue)
                startActivity(intent)
            }
        }



    }

    private fun countCalories (wei: Double, hei: Int, ag: Int, se: Int, activ: Double) = ((10*wei)+(6.25*hei)-(5*ag) + se)*activ

    private fun countCaloriesUS (wei: Double, hei: Int, heiInch: Double, ag: Int, se: Int, activ: Double) = ((10*(wei*0.45))+(6.25*((hei*30.48)+(heiInch*2.54)))-(5*ag) + se)*activ
}