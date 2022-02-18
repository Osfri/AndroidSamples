package com.example.work06

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, filename:String) : SQLiteOpenHelper(context,filename,null,1) {

    companion object{
        private var dbHelper:DBHelper? = null
        fun getInstance(context: Context, filename: String) : DBHelper{
            if (dbHelper == null){
                dbHelper = DBHelper(context,filename)
            }
            return dbHelper!!
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = " CREATE TABLE if not exists DATA(" +
                " seq integer primary key autoincrement," +
                " imgaddr string, " +
                " title string," +
                " date date," +
                " address string)"
        db?.execSQL(sql)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    fun insert(vo:VO){
        var sql = "INSERT INTO DATA(imgaddr,title,date,address)" +
                " VALUES('${vo.imgaddr}','${vo.title}',datetime('now','localtime'),'${vo.address}')"
        var db = this.writableDatabase
        db.execSQL(sql)
    }
    @SuppressLint("Range")
    fun listsearch() : MutableList<VO>{
        var db = this.writableDatabase
        var list: MutableList<VO> = mutableListOf()
        var c: Cursor? = db.rawQuery("SELECT * FROM DATA",null)

        if (c?.getCount() == 0){
            val test:VO = VO(0,"","","","")
            list.add(test)
        }else {
            c?.moveToFirst()
            for (i in 0 until c?.getCount()!!){
                val seq = c.getString(c.getColumnIndex("seq"))
                val imgaddr = c.getString(c.getColumnIndex("imgaddr"))
                val title = c.getString(c.getColumnIndex("title"))
                val date = c.getString(c.getColumnIndex("date"))
                val address = c.getString(c.getColumnIndex("address"))
                val test: VO = VO(seq?.toInt()!!,imgaddr,title,date,address)
                list.add(test)
                c.moveToNext()
            }
        }
        return list
    }
}