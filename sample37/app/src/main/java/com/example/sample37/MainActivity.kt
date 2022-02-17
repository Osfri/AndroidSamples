package com.example.sample37

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linerLayout = findViewById<LinearLayout>(R.id.LinerLayout)

        var childLayout:LinearLayout? = null


        for (i in 0 until 10){

            if (i % 3 == 0) {

                childLayout = LinearLayout(this)
                childLayout.orientation = LinearLayout.HORIZONTAL
                val layoutParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200)
                childLayout.layoutParams = layoutParam

            }

            val btnParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
            btnParams.weight = 1.0f

            val normalBtn = Button(this).apply {

                text = (i + 1).toString()
                layoutParams = btnParams
                id = i

                setOnClickListener {
                    val myToast = Toast.makeText(this.context,"${id + 1}번 버튼", Toast.LENGTH_SHORT)
                    myToast.show()

                }




            }

            childLayout?.addView(normalBtn)

            if (i % 3 == 2 || i == (10-1)){
                linerLayout.addView(childLayout)
            }

        }

    }
}