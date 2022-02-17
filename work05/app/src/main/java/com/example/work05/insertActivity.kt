package com.example.work05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioGroup

class insertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val radioGroup = findViewById<RadioGroup>(R.id.insert_radio)
        val title = findViewById<EditText>(R.id.insert_title)
        val date = findViewById<DatePicker>(R.id.insert_date)
        val pay = findViewById<EditText>(R.id.insert_pay)
        val content = findViewById<EditText>(R.id.insert_content)
        val insert = findViewById<Button>(R.id.insert_insert)
        val home = findViewById<Button>(R.id.insert_main)

        var radioCheck = ""

        insert.setOnClickListener {
            when(radioGroup.checkedRadioButtonId){
                R.id.insert_gain -> radioCheck = "수입"
                R.id.insert_nogain -> radioCheck = "지출"
            }
            val mydate = String.format("%d-%02d-%02d", date.year, date.month+1, date.dayOfMonth)
            val datavo = DataVO(0,radioCheck,title.text.toString(),mydate,pay.text.toString(),content.text.toString())
            DBHelper.getInstance(this,"paydata.db").insert(datavo)
        }

        home.setOnClickListener {
            val nextIntent = Intent(this,MainActivity::class.java)
            startActivity(nextIntent)
        }

    }
}