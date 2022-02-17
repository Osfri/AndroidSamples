package com.example.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val namename = findViewById<TextView>(R.id.textName)
        val age = findViewById<TextView>(R.id.textAge)
        val address = findViewById<TextView>(R.id.textAddress)

        val searchText = findViewById<EditText>(R.id.editTextSearch)

        val searchBtn = findViewById<Button>(R.id.memberSearchBtn)

        searchBtn.setOnClickListener {

            val name:String = searchText.text.toString()

            val memberInfo = DBHelper.getInstance(this,"member.db").search(name)

            namename.text = memberInfo.name
            age.text = memberInfo.age.toString()
            address.text = memberInfo.address

        }
    }
}