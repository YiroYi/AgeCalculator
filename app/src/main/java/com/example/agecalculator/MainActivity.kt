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
  private var tvTimeInMinutes: TextView? = null

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
    tvSelectedDate = findViewById(R.id.tvSelectedDate)
    tvTimeInMinutes = findViewById(R.id.tvTimeInMinutes)

    btnDatePicker.setOnClickListener {
      clickDatePicker()
    }
  }

  @RequiresApi(Build.VERSION_CODES.N)
  private fun clickDatePicker() {

    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)

    val dpd = DatePickerDialog(
      this,
      DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
        Toast.makeText(
          this,
          "Year was $selectedYear, month was $selectedMonth, day is $selectedDayOfMonth ",
          Toast.LENGTH_LONG
        ).show()

        val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDayOfMonth"

        tvSelectedDate?.text = selectedDate


        val convertedSimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        val theDate = convertedSimpleDateFormat.parse(selectedDate)

        theDate?.let{
          val selectedDateInMinutes = theDate.time / 60000

          val currentDate = convertedSimpleDateFormat.parse(convertedSimpleDateFormat.format(System.currentTimeMillis()))
          currentDate?.let{
            val currentDateInMinutes = currentDate.time / 60000

            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

            tvTimeInMinutes?.text = differenceInMinutes.toString()
          }

        }

      },
      year,
      month,
      day
    )

    dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
    dpd.show()
  }
}