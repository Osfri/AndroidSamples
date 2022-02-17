package com.example.sample21

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoview = findViewById<VideoView>(R.id.videoView)

        videoview.setMediaController(MediaController(this))

        videoview.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.music))
    }
}