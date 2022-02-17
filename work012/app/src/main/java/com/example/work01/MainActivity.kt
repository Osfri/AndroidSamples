package com.example.work01

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.work01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)*/
        val binding by lazy  { ActivityMainBinding.inflate(layoutInflater) }
        setContentView(binding.root)
        val textView = findViewById<TextView>(R.id.textView)
        val start = findViewById<Button>(R.id.button1)
        val replay = findViewById<Button>(R.id.button2)

        var count:Int = 0
        var random1:Int? = 0
        var num1:Int? = 0
        var random2:Int? = 0
        var num2:Int? = 0
        var random3:Int? = 0
        var num3:Int? = 0
        var strike:Int = 0
        var ball:Int = 0
        var data = listOf(1,2,3,4,5,6,7,8,9,10)
        var index:Int = 0

        textView.text = "게임시작"

        random1 = (1..10).random()
        random2 = (1..10).random()
        random3 = (1..10).random()
        while (true){
            if ((random1 == random3) || (random1 == random2) || (random2 == random3)){
                random1 = (1..10).random()
                random2 = (1..10).random()
                random3 = (1..10).random()
            }else{
                println("${random1}${random2}${random3}")
                Toast.makeText(this@MainActivity,"${random1}${random2}${random3}",Toast.LENGTH_SHORT).show()
                break
            }
        }

        val myAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,data)
        binding.spinner1.adapter = myAdapter
        binding.spinner2.adapter = myAdapter
        binding.spinner3.adapter = myAdapter

        binding.spinner1.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                num1 = p2+1
//                Toast.makeText(this@MainActivity,"${num1}",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.spinner2.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                num2 = p2+1
//                Toast.makeText(this@MainActivity,"${num2}",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.spinner3.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                num3 = p2+1
//                Toast.makeText(this@MainActivity,"${num3}",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        start.setOnClickListener{
            if (count > 10){
                Toast.makeText(this@MainActivity,"기회가 없습니다",Toast.LENGTH_SHORT).show()
            }else{

                if ((num1 == num2) || (num2 == num3) || (num1 == num3)){
                    Toast.makeText(this@MainActivity,"같은숫자가 있습니다",Toast.LENGTH_SHORT).show()
                }else{
                    count++
                    strike = 0
                    ball = 0
                    if (num1 == random1){
                        strike++
                    }
                    if (num2 == random2){
                        strike++
                    }
                    if (num3 == random3){
                        strike++
                    }
                    if (num1 == random2){
                        ball++
                    }
                    if (num1 == random3){
                        ball++
                    }
                    if (num2 == random3){
                        ball++
                    }
                    if (num2 == random1){
                        ball++
                    }
                    if (num3 == random1){
                        ball++
                    }
                    if (num3 == random2){
                        ball++
                    }
                    if (strike == 3){
                        textView.text = "3Strike!! Clear!!!"
                    }else{
                        textView.text = "${strike}Strike ${ball}ball 남은기회:${11-count}"

                    }


                }
            }
        }
        replay.setOnClickListener {
            count = 0
            random1 = (1..10).random()
            random2 = (1..10).random()
            random3 = (1..10).random()
            while (true){
                if ((random1 == random3) || (random1 == random2) || (random2 == random3)){
                    random1 = (1..10).random()
                    random2 = (1..10).random()
                    random3 = (1..10).random()
                }else{
                    println("${random1}${random2}${random3}")
                    Toast.makeText(this@MainActivity,"${random1}${random2}${random3}",Toast.LENGTH_SHORT).show()
                    break
                }
            }
            textView.text = "게임시작"
        }

    }
}
