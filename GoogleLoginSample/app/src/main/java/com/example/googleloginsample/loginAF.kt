package com.example.googleloginsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class loginAF : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_af)

        val img = findViewById<ImageView>(R.id.imageView)
        val textView = findViewById<TextView>(R.id.textView2)

        val nickName:String? = intent.getStringExtra("Nickname")
        val imgUrl:String? = intent.getStringExtra("imgUrl")
        println("==============${nickName}${imgUrl}")

        textView.text = nickName
        Glide.with(this).load(imgUrl).into(img)
    }
}