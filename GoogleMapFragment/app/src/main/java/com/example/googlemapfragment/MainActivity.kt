package com.example.googlemapfragment

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val address = findViewById<EditText>(R.id.address)
        val txt1 = findViewById<EditText>(R.id.text1)
        val txt2 = findViewById<EditText>(R.id.text2)
        val Button = findViewById<Button>(R.id.button)

        val geocoder: Geocoder = Geocoder(this)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.content, MapsFragment(this))
        fragmentTransaction.commit()

        Button.setOnClickListener {
            if (address.text == null){
                val d1: Double = txt1.text.toString().toDouble()
                val d2: Double = txt2.text.toString().toDouble()
                MapsFragment(this).setLocation(d1,d2)
            }else{
                var list:List<Address>? = null
                val str = address.text.toString()

                try {
                    list = geocoder.getFromLocationName(str, 10)
                }catch (e:IOException){

                }
                if (list != null){
                    if (list!!.isEmpty()){
                        Toast.makeText(this,"해당되는 주소는 없습니다",Toast.LENGTH_SHORT).show()
                    }else{
                        val mark1:Double = list!!.get(0).latitude.toString().toDouble()
                        val mark2:Double = list!!.get(0).longitude.toString().toDouble()
                        println("-------------"+ mark1)
                        println("-------------"+ mark2)

                        MapsFragment(this).setLocation(mark1,mark2)
                        var Bundle = Bundle()
                        Bundle.putDouble("a",mark1)
                        Bundle.putDouble("b",mark2)
                    }
                }

            }

        }
    }
}

//  AIzaSyDCNwzCcJ1FbybUIuHCFZnJh8t1ULL8eSo