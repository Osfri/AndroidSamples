package com.example.sample30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//build.gradle 3개 추가
class MainActivity : AppCompatActivity() {

    var userList = arrayListOf<DataVO>(
        DataVO("김철수","kcs","서울시",3000000,"kim"),
        DataVO("박상현","psh","부산시",5000000,"park"),
        DataVO("최진형","cjh","광주시",4000000,"choi"),
        DataVO("정수동","jsd","충주시",4500000,"jung"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.recycler_View)

        val mAdapter = CustomAdapter(this,userList)
        recyclerView.adapter = mAdapter

        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout

        recyclerView.setHasFixedSize(true)

    }
}