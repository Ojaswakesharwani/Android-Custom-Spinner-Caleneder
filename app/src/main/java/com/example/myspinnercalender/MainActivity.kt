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
    private val tag = "DatePickerLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datepicker.apply {
            setOffset(3)
            setTextSize(17)
            setDate(getCurrentTime())
            setDarkModeEnabled(true)

            setDataSelectListener(object : DatePicker.DataSelectListener {
                override fun onDateChanged(date: Long, day: Int, month: Int, year: Int) {
                    val formattedDate = "${date}/${month + 1}/$year"
                    binding.btnCalender.text = "Changing:\n$formattedDate"
                }

                @SuppressLint("SetTextI18n")
                override fun onDateSelected(date: Long, day: Int, month: Int, year: Int) {
                    val formattedDate = "${date}/${month + 1}/$year"
                    binding.btnCalender.text = "Selected:\n$formattedDate"
                }
            })
        }

        // Log Initial Date
        val initialDate = getCurrentTime()
        Log.d(tag, "Initial Date Set: ${convertToReadableDate(initialDate)}")
    }

    // Convert timestamp to a readable date format
    private fun convertToReadableDate(timestamp: Long): String {
        val date = java.util.Date(timestamp)
        val format = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        return format.format(date)
    }
}
