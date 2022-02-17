package com.example.work05

import android.os.Parcel
import android.os.Parcelable

class DataVO(val seq:Int,val gain:String?,val title:String?, val date:String?, val pay:String?, val content:String?) : Parcelable{
    constructor(parcel:Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){}
    override fun writeToParcel(parcel: Parcel, p1:Int){
        parcel.writeInt(seq)
        parcel.writeString(gain)
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(pay)
        parcel.writeString(content)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<DataVO>{
        override fun createFromParcel(parcel: Parcel): DataVO {
            return DataVO(parcel)
        }
        override fun newArray(size: Int): Array<DataVO?> {
            return arrayOfNulls(size)
        }
    }
}