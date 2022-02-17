package com.example.sample36

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?,name:String?,factory: SQLiteDatabase.CursorFactory?,version:Int)
: SQLiteOpenHelper(context,name,factory,version) {

    override fun onCreate(db: SQLiteDatabase?) {
                                            //존재하지(EXISTS) 않는다면
        var sql : String = " CREATE TABLE IF NOT EXISTS MYTABLE(" +
                           "    SEQ INTEGER PRIMARY KEY AUTOINCREMENT, " +
                           "    TXT TEXT)"

        db?.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        var sql : String = " DROP TABLE IF EXISTS MYTABLE "
        db?.execSQL(sql)
        onCreate(db)

    }

    fun insert(db: SQLiteDatabase, txt:String){

        var sql = " INSERT INTO MYTABLE('TXT') " +
                  " VALUES('${txt}') "

        db.execSQL(sql)

    }

}