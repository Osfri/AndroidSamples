package com.example.sample36

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbHelper = DBHelper(this, "mydb.db",null,1)

        val insertBtn = findViewById<Button>(R.id.insertBtn)
        val editInsert = findViewById<EditText>(R.id.editInsert)

        var database = dbHelper.writableDatabase

        insertBtn.setOnClickListener {

            val text = editInsert.text

            dbHelper.insert(database,text.toString())

        }

    }
}