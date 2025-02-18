package com.example.myspinnercalender

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myspinnercalender.databinding.ActivityMainBinding
import com.example.myspinnercalender.utils.DateUtils.getCurrentTime
import com.example.myspinnercalender.view.DatePicker


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datepicker.apply {
            setOffset(3)
            setTextSize(17)
            setDate(getCurrentTime())
            this.setDarkModeEnabled(true)
            setDataSelectListener(object : DatePicker.DataSelectListener {
                override fun onDateChanged(date: Long, day: Int, month: Int, year: Int) {
                    Log.e(TAG, "changing: " + day + "/" + (month + 1) + "/" + year, )
                    binding.btnCalender.text = "Changing\n" + day + "/" + (month + 1) + "/" + year                }

                @SuppressLint("SetTextI18n")
                override fun onDateSelected(date: Long, day: Int, month: Int, year: Int) {
                    binding.btnCalender.text = "" + day + "/" + (month + 1) + "/" + year
                    Log.e(TAG, "onDateSelected: " + day + "/" + (month + 1) + "/" + year, )
                }

            })
        }

    }
}