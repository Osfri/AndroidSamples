package com.example.work01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count:Int = 0
        val textview = findViewById<TextView>(R.id.textView)
        val countup = findViewById<Button>(R.id.button)
        val countdown = findViewById<Button>(R.id.button2)
        val reset = findViewById<Button>(R.id.button3)
        val editText = findViewById<EditText>(R.id.editText)
        val set = findViewById<Button>(R.id.button4)

        countup.setOnClickListener {
            count++
            textview.setText(""+count)
        }
        countdown.setOnClickListener {
            count--
            textview.setText(""+count)
        }
        reset.setOnClickListener {
            count = 0
            textview.setText(""+count)
        }
        set.setOnClickListener {
            val index:Int = Integer.parseInt(editText.text.toString())
            count = index
            textview.setText(""+count)
        }
    }
}