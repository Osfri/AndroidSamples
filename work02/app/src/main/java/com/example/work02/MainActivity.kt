package com.example.work02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.work02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
        setContentView(binding.root)
        val set = findViewById<Button>(R.id.set)
        val start = findViewById<Button>(R.id.start)
        var index:Int = 0
        var BtnCount:Int = 0


        val data = listOf(1,2,3,4,5,6,7,8,9)

        val myAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data)
        binding.spinner.adapter =myAdapter

        binding.spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                index = p2+1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) { }

        }
        set.setOnClickListener {
            BtnCount = index
        }

        start.setOnClickListener {

            val nextIntent = Intent(this,game::class.java)
            val Btncnt:String = BtnCount.toString()
            nextIntent.putExtra("number",BtnCount)

            startActivity(nextIntent)

        }





    }
}