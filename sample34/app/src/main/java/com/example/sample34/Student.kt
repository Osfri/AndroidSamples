package com.example.sample34

import android.os.Parcel
import android.os.Parcelable

class Student(var name:String?, var count:Int , var level:String?) : Parcelable {

    constructor(parcel:Parcel):this(parcel.readString(),parcel.readInt(),parcel.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(name)
        parcel?.writeInt(count)
        parcel?.writeString(level)
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

}