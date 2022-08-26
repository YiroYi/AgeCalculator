package com.example.agecalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
  private var tvSelectedDate: TextView? = null

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
    tvSelectedDate = findViewById(R.id.tvSelectedDate)
    btnDatePicker.setOnClickListener {
      clickDatePicker()
    }
  }

  @RequiresApi(Build.VERSION_CODES.N)
  fun clickDatePicker() {

    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
      this,
      DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
        Toast.makeText(
          this,
          "Year was $selectedYear, month was $selectedMonth, day is $selectedDayOfMonth ",
          Toast.LENGTH_LONG
        ).show()

        val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDayOfMonth"
        tvSelectedDate?.text = selectedDate

        val convertedSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        val theDate = convertedSimpleDateFormat.parse(selectedDate)
      },
      year,
      month,
      day
    ).show()
  }
}