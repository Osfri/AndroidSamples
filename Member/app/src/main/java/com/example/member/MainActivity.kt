package com.example.member

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val searchBtn = findViewById<Button>(R.id.SearchBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)

        insertBtn.setOnClickListener {
            val i = Intent(this, InsertActivity::class.java)
            startActivity(i)
        }
        searchBtn.setOnClickListener {
            val i = Intent(this, SearchActivity::class.java)
            startActivity(i)
        }
        deleteBtn.setOnClickListener {

        }
    }
}







