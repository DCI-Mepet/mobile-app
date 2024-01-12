package com.bangkit.ch2_ps178_android.data.dataclass

import android.os.Parcel
import android.os.Parcelable

data class InputModal(
    val namaLengkap: String = "null",
    val email: String = "null",
    val nomor: String = "null",
    var catatan: String = "null"
) : Parcelable {
    // Implementasi Parcelable
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(namaLengkap)
        parcel.writeString(email)
        parcel.writeString(nomor)
        parcel.writeString(catatan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InputModal> {
        override fun createFromParcel(parcel: Parcel): InputModal {
            return InputModal(parcel)
        }

        override fun newArray(size: Int): Array<InputModal?> {
            return arrayOfNulls(size)
        }
    }
}


