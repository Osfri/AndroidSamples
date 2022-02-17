package com.example.sample09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



       setContentView(R.layout.activity_main)

        val btn2 = findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener {
            Toast.makeText(this.applicationContext,"버튼2 클릭!",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn->{
                Log.d("버튼","클릭")

                var btn2 = findViewById<Button>(R.id.btn2)
                btn2.text = SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(Date())
            }
        }
    }
}