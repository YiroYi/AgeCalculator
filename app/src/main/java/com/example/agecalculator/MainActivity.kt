package com.example.agecalculator

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

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
      DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        Toast.makeText(
          this,
          "Date Picker Works",
          Toast.LENGTH_LONG
        ).show()
      },
      year,
      month,
      day
    ).show()
  }
}