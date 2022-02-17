package com.example.work05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class dateSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_search)
        val date1 = findViewById<DatePicker>(R.id.search_date_start)
        val date2 = findViewById<DatePicker>(R.id.search_date_end)
        val search = findViewById<Button>(R.id.search_search)
        val home = findViewById<Button>(R.id.search_main)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        search.setOnClickListener {
            val startDate = String.format("%d-%02d-%02d", date1.year, date1.month+1, date1.dayOfMonth)
            val endDate = String.format("%d-%02d-%02d", date2.year, date2.month+1, date2.dayOfMonth)
            val info = DBHelper.getInstance(this,"paydata.db").listsearch(startDate,endDate)

            if (info[0].seq != 0){
                val mAdapter = CustomAdapter(this, info!!)
                recyclerView.adapter = mAdapter
                val layout = LinearLayoutManager(this)
                recyclerView.layoutManager = layout
                recyclerView.setHasFixedSize(true)
            }else{
                Toast.makeText(this, "정보가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}