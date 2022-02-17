package com.example.sample34

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editName = findViewById<EditText>(R.id.editName)
        val editCount = findViewById<EditText>(R.id.editCount)
        val editLevel = findViewById<EditText>(R.id.editLevel)

        val move = findViewById<Button>(R.id.move)

        move.setOnClickListener {

            var student = Student(editName.text.toString(),editCount.text.toString().toInt(),editLevel.text.toString())

            //이동준비
            val nextIntent = Intent(this,SecondActivity::class.java)

            // 짐싸기
            nextIntent.putExtra("student",student) //오브젝트를 보낼수없으니 파셀레이블로 변환

            //이동
            startActivity(nextIntent)

        }
    }
}