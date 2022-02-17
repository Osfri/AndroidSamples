package com.example.sample

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //리소스 객체화
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)

//        binding.btnSay.setOnClickListener {
//            Log.i(ContentValues.TAG,"btnSay 클릭했습니다.")
//            println("btnSay 클릭했습니다.")
//
//            binding.textSay.setText("hello Android")
//        }
        setContentView(R.layout.activity_main)

        val textSay:TextView = findViewById(R.id.textSay)
        val btnSay = findViewById<Button>(R.id.btnSay)

        btnSay.setOnClickListener {
            textSay.text = "Android Wrold"

            // Toast 얼러트랑 비슷하지만 가벼운 메세지
            val toast = Toast.makeText(this.applicationContext,"버튼 클릭",Toast.LENGTH_SHORT)
            toast.show()

            // 얼러트
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Welcome")
                .setMessage("어서오세요!!")
                .setCancelable(false)
                .setNeutralButton("닫기!",DialogInterface.OnClickListener{dialog, which->
                    // 닫기 버튼을 클릭시
                }).show()
        }


        Log.i(ContentValues.TAG,"onCreate 실행")
    }

    override fun onPause() {
        super.onPause()
        Log.i(ContentValues.TAG,"onCreate 실행")
    }

    override fun onStop() {
        super.onStop()
        Log.i(ContentValues.TAG,"onStop 실행")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(ContentValues.TAG,"onDestroy 실행")
    }

    override fun onStart() {
        super.onStart()
        Log.i(ContentValues.TAG,"onStart 실행")
    }

    override fun onResume() {
        super.onResume()
        Log.i(ContentValues.TAG,"onResume 실행")

    }
}