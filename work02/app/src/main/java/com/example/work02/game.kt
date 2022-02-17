package com.example.work02

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)

        var childLayout:LinearLayout? = null

        val move = findViewById<Button>(R.id.move)

        val index = intent.getIntExtra("number",1)

        val btnCount = index

        val random = (0 until  btnCount).random()
        println(random)

        for (i in 0 until btnCount){

            if (i % 3 == 0 ){

                childLayout = LinearLayout(this)
                childLayout.orientation = LinearLayout.HORIZONTAL
                val layoutParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150)
                childLayout.layoutParams = layoutParam

            }

            val btnParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
            btnParams.weight = 1.0f

            val normalBtn = Button(this).apply {

                text = (i + 1).toString()
                layoutParams = btnParams
                id = i

                setOnClickListener {

                    if (random == id){
                        text = "빙고!"
                        setBackgroundColor(Color.parseColor("#ffcccc"))
                        setEnabled(false)
                    }else{
                        text = "SAFE!"
                        setBackgroundColor(Color.parseColor("#afe3ff"))
                        setEnabled(false)
                    }
                }
            }

            childLayout?.addView(normalBtn)

            if(i % 3 == 2 || i == (btnCount-1)){
                linearLayout.addView(childLayout)
            }

        }

        move.setOnClickListener {
            val nextIntent = Intent(this,MainActivity::class.java)
            startActivity(nextIntent)
        }
    }
}