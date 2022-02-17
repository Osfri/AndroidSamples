package com.example.member

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Dao ==
// Singleton
class DBHelper(context:Context, filename:String) : SQLiteOpenHelper(context, filename, null, 1) {

    companion object{
       private var dbhelper:DBHelper? = null
       fun getInstance(context:Context, filename: String) : DBHelper {
           if(dbhelper == null){
               dbhelper = DBHelper(context, filename)
           }
           return dbhelper!!
       }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql:String = " CREATE TABLE if not exists MEMBER( " +
                            "  seq integer primary key autoincrement, " +
                            "  name string, " +
                            "  age integer, " +
                            "  address string ) "
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insert(vo:Member){
        var sql = " INSERT INTO MEMBER(name, age, address) " +
                "   VALUES('${vo.name}', ${vo.age}, '${vo.address}') "
        var db = this.writableDatabase
        db.execSQL(sql)
    }
    fun delete(seq:Int){

    }
    @SuppressLint("Range")
    fun search(a:String): Member {

        var db = this.writableDatabase

//        var query = "SELECT * FROM MEMBER WHERE NAME = ${a}"
        var c:Cursor = db.rawQuery("SELECT * FROM MEMBER WHERE NAME = '$a'",null)

        c.moveToFirst() //시발 이것때매 몇시간 해맸누

        val seq = c.getString(c.getColumnIndex("seq"))
        val name = c.getString(c.getColumnIndex("name"))
        val age = c.getString(c.getColumnIndex("age"))
        val address = c.getString(c.getColumnIndex("address"))

        val a:Member = Member(seq.toInt(),name,age.toInt(),address)

        return a

    }

}








