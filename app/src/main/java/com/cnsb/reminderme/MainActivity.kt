package com.cnsb.reminderme

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedTime: TextView
    private lateinit var etName: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPickTime = findViewById<Button>(R.id.btnPickTime)
        val btnSave = findViewById<Button>(R.id.btnSave)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        etName = findViewById(R.id.etName)

        btnPickTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, h, m ->
                val time = String.format("%02d:%02d", h, m)
                tvSelectedTime.text = "Waktu untuk Minum: $time"
            }, hour, minute, true)

            timePicker.show()
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val time = tvSelectedTime.text.toString()

            if (name.isEmpty() || time == "Waktu Belum Dipilih") {
                Toast.makeText(this, "Ayo lengkapi namamu terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Halo $name, jangan lupa minum air ya!\n$time", Toast.LENGTH_LONG).show()
            }
        }
    }

}