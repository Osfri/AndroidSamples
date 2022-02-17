package com.example.work05

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi

class searchActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        
        val date = findViewById<DatePicker>(R.id.search_one_date)
        val result = findViewById<TextView>(R.id.search_one_result)
        val home = findViewById<Button>(R.id.search_one_main)
        
        date.setOnDateChangedListener { datePicker, i, i2, i3 ->
            val date = String.format("%d-%02d-%02d", i, i2+1, i3)
            val info = DBHelper.getInstance(this,"paydata.db").search(date)

            if (info.seq == 0){
                result.text = "현재 날짜에 수입 또는 지출이 없습니다."
            }else {
                result.text = "종류:${info.gain} 제목:${info.title} 날짜:${info.date} 금액:${info.pay} 메모:${info.content}"
            }
        }
        home.setOnClickListener {
            val nextIntent = Intent(this,MainActivity::class.java)
            startActivity(nextIntent) }
        fun aa(a:Int) : Int {
            return a
        }
        
    }
}