package com.example.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val insert = findViewById<Button>(R.id.insert)

        val insertName = findViewById<EditText>(R.id.insertName)
        val insertAge = findViewById<EditText>(R.id.insertAge)
        val insertAddress = findViewById<EditText>(R.id.insertAddress)

        insert.setOnClickListener {

            val mem = Member(0,
                            insertName.text.toString(),
                            insertAge.text.toString().toInt(),
                            insertAddress.text.toString())

            DBHelper.getInstance(this, "member.db").insert(mem)

        }

    }
}