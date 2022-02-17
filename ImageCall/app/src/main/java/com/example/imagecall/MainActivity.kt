package com.example.imagecall

import android.Manifest
import android.graphics.BitmapFactory
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File
import java.nio.file.Files.exists

class MainActivity : AppCompatActivity() {
                                        //권한들
    lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ results->
            if (!results.all { it.value }){
                Toast.makeText(this,"권한 승인 필요",Toast.LENGTH_SHORT).show()
            }
        }
        locationPermission.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )

        val imageView = findViewById<ImageView>(R.id.imageView)

        val file:File = File("/storage/emulated/0/Pictures/20220216111311.jpg")
        val fExist = file.exists() // boolean 파일이 있냐 없냐

        if (fExist){
            Log.d("","파일이 있다")
            val myBitmap = BitmapFactory.decodeFile("/storage/emulated/0/Pictures/20220216111311.jpg")

            imageView.setImageBitmap(myBitmap)
        }else{
            Log.d("","파일이 없다")
        }
    }
}