package com.bangkit.ch2_ps178_android.data.dataclass

import android.os.Parcel
import android.os.Parcelable


data class PassingData(
    val mainAdapterRow: MainAdapterRow,
    val dataInput: InputModal,
    val tanggal: String = "",
    val checkIn: String = "",
    val checkOut: String = "",
    var total_pembayaran : String = ""

) : Parcelable {
    // Implementasi Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(MainAdapterRow::class.java.classLoader)!!,
        parcel.readParcelable(InputModal::class.java.classLoader)!!,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(mainAdapterRow, flags)
        parcel.writeParcelable(dataInput, flags)
        parcel.writeString(tanggal)
        parcel.writeString(checkIn)
        parcel.writeString(checkOut)
        parcel.writeString(total_pembayaran)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PassingData> {
        override fun createFromParcel(parcel: Parcel): PassingData {
            return PassingData(parcel)
        }

        override fun newArray(size: Int): Array<PassingData?> {
            return arrayOfNulls(size)
        }
    }
}