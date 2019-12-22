package com.example.exercise4

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var age : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myFormat = "dd-MM-yyyy"

        val sdf = SimpleDateFormat(myFormat, Locale.US)
        txtSelectDOB.text = sdf.format(System.currentTimeMillis())

        val calender = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, monthOfYear)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            txtSelectDOB.text = sdf.format(calender.time)
            age = calender.get(Calendar.YEAR)

            txtAge.text = CalculateAge(age).toString()
        }

        txtSelectDOB.setOnClickListener{
            DatePickerDialog(this@MainActivity, dateSetListener,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnOK.setOnClickListener(){
            txtMinBasicSaving.text  = "RM " + CalculateMinBasicSaving(CalculateAge(age))
            txtInvestment.text = "RM " + String.format("%.2f", calculateInvestment())
        }

        btnReset.setOnClickListener(){
            txtInvestment.text = ""
            txtInvestment.text = ""
            txtAge.text  = ""
            txtSelectDOB.text = sdf.format(System.currentTimeMillis())
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show()
        }
    }

    private fun CalculateAge(year: Int): Int {
        return Calendar.getInstance().get(Calendar.YEAR) - year
    }

    private fun CalculateMinBasicSaving(age : Int):Int{
        var minBasicSaving:Int = 0

        when(age){
            in 16..20 -> minBasicSaving = 5000
            in 21..25 -> minBasicSaving = 14000
            in 26..30 -> minBasicSaving = 29000
            in 31..35 -> minBasicSaving = 50000
            in 36..40 -> minBasicSaving = 78000
            in 41..45 -> minBasicSaving = 116000
            in 46..50 -> minBasicSaving = 165000
            in 51..55 -> minBasicSaving = 228000
        }
        return minBasicSaving
    }

    private fun calculateInvestment(): Double {
        return CalculateMinBasicSaving(CalculateAge(age)) * 0.3
    }
}
