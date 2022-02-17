package com.example.work05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insert = findViewById<Button>(R.id.main_insert)
        val search = findViewById<Button>(R.id.main_search)
        val dateInsert = findViewById<Button>(R.id.main_dateSearch)

        insert.setOnClickListener {
            val nextIntent = Intent(this,insertActivity::class.java)
            startActivity(nextIntent)
        }
        search.setOnClickListener {
            val nextIntent = Intent(this,searchActivity::class.java)
            startActivity(nextIntent)
        }
        dateInsert.setOnClickListener {
            val nextIntent = Intent(this,dateSearchActivity::class.java)
            startActivity(nextIntent)
        }

    }
}