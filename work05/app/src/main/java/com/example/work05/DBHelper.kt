package com.example.work05

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DBHelper(context: Context,filename:String) : SQLiteOpenHelper(context,filename,null,1) {

    companion object{
        private var dbHelper:DBHelper? = null
        fun getInstance(context: Context,filename: String) : DBHelper{
            if (dbHelper == null){
                dbHelper = DBHelper(context,filename)
            }
            return dbHelper!!
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = " CREATE TABLE if not exists PAYDATA(" +
                " seq integer primary key autoincrement," +
                " gain string, " +
                " title string," +
                " date date," +
                " pay string," +
                " content string ) "
        db?.execSQL(sql)
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}



        fun insert(vo:DataVO){
            var sql = " INSERT INTO PAYDATA(gain, title, date, pay, content)" +
                    " VALUES('${vo.gain}','${vo.title}','${vo.date}','${vo.pay}','${vo.content}') "
            var db = this.writableDatabase
            db.execSQL(sql)
        }
        @SuppressLint("Range")
        fun search(a:String) : DataVO{
            var db = this.writableDatabase
            var c:Cursor? = db.rawQuery("SELECT * FROM PAYDATA WHERE DATE = '${a}'",null)

            if (c?.getCount() == 0){
                val a:DataVO = DataVO(0,"","","","","")
                return a
            }else {
                c?.moveToFirst()
                val seq = c?.getString(c.getColumnIndex("seq"))
                val gain = c?.getString(c.getColumnIndex("gain"))
                val title = c?.getString(c.getColumnIndex("title"))
                val date = c?.getString(c.getColumnIndex("date"))
                val pay = c?.getString(c.getColumnIndex("pay"))
                val content = c?.getString(c.getColumnIndex("content"))
                val a: DataVO = DataVO(seq?.toInt()!!, gain, title, date, pay, content)
                return a
            }
        }
        @SuppressLint("Range")
        fun listsearch(start:String, end:String) : MutableList<DataVO>{
            var db = this.writableDatabase
            var list: MutableList<DataVO> = mutableListOf()
            var c:Cursor? = db.rawQuery("SELECT * FROM PAYDATA WHERE DATE BETWEEN '${start}' AND '${end}' ",null)

            if (c?.getCount() == 0){
                val test:DataVO = DataVO(0,"","","","","")
                list.add(test)
            }else {
                c?.moveToFirst()
                for (i in 0 until c?.getCount()!!){
                    val seq = c.getString(c.getColumnIndex("seq"))
                    val gain = c.getString(c.getColumnIndex("gain"))
                    val title = c.getString(c.getColumnIndex("title"))
                    val date = c.getString(c.getColumnIndex("date"))
                    val pay = c.getString(c.getColumnIndex("pay"))
                    val content = c.getString(c.getColumnIndex("content"))
                    val test: DataVO = DataVO(seq?.toInt()!!,gain,title,date,pay,content)
                    list.add(test)
                    c.moveToNext()
                }
            }
                return list
        }
}