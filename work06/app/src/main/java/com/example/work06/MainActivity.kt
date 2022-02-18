package com.example.work06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.button)

        var recyclerView = findViewById<RecyclerView>(R.id.recycle)
        val info = DBHelper.getInstance(this,"DATA.db").listsearch()


        if (info[0].seq !=0){
            val mAdapter = CustomAdapter(this, info)
            recyclerView.adapter = mAdapter
            val layout = LinearLayoutManager(this)
            recyclerView.layoutManager = layout
            recyclerView.setHasFixedSize(true)
        }

        recyclerView.setOnClickListener {

        }
        btn.setOnClickListener {
            val nextIntent = Intent(this,InsertActivity::class.java)
            startActivity(nextIntent)
        }

    }

}