package com.example.work06

import android.os.Parcel
import android.os.Parcelable

class VO(val seq:Int, val imgaddr:String?, val title:String?,val date:String?,val address:String?) : Parcelable {
    constructor(parcel: Parcel):this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    ){}
    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeInt(seq)
        parcel.writeString(imgaddr)
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VO> {
        override fun createFromParcel(parcel: Parcel): VO {
            return VO(parcel)
        }

        override fun newArray(size: Int): Array<VO?> {
            return arrayOfNulls(size)
        }
    }
}