package com.example.work06

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.maps.MapFragment
import java.io.File
import java.io.IOException

class detail : AppCompatActivity() {
    companion object{
        var lat:Double = 0.0
        var lon:Double = 0.0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<VO>("data")

        val img = findViewById<ImageView>(R.id.detail_img)
        val title = findViewById<TextView>(R.id.detail_title)
        val date = findViewById<TextView>(R.id.detail_date)
        val address = findViewById<TextView>(R.id.detail_address)
        val map = findViewById<FrameLayout>(R.id.detail_map)

        val file: File = File(data?.imgaddr)
        if (file.exists()) run {
            val bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
            img.setImageBitmap(bitmap)
        }
        title.text = data?.title
        date.text = data?.date
        address.text = data?.address

        val fm = supportFragmentManager
        val maps = MapsActivity(this)
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.detail_map,maps)
        fragmentTransaction.commit()

        var list:List<Address>? = null
        val str = address.text.toString()

        val geocoder:Geocoder = Geocoder(this)


        try {
            list = geocoder.getFromLocationName(str,10)
        }catch (e:IOException){}

        if (list != null) {
            if (list.isEmpty()) {
                Toast.makeText(this, "해당되는 주소는 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                lat = list.get(0).latitude
                lon = list.get(0).longitude

//                maps.setLocation(lat,lon)
            }
        }




    }

}