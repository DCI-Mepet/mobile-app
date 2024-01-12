package com.bangkit.ch2_ps178_android.data.dataclass

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MainAdapterRow(
    @SerializedName("no") val no: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String,
    @SerializedName("vicinity") val vicinity: String,
    @SerializedName("kecamatan") val kecamatan: String,
    @SerializedName("kabupaten") val kabupaten: String,
    @SerializedName("rating") val rating: String,
    @SerializedName("user_ratings_total") val userRatingsTotal: String,
    @SerializedName("price") var price: String,
    @SerializedName("jenis_sepakbola") val jenisSepakbola: String,
    @SerializedName("jenis_badminton") val jenisBadminton: String,
    @SerializedName("jenis_tenis") val jenisTenis: String,
    @SerializedName("jenis_voli") val jenisVoli: String,
    @SerializedName("jenis_futsal") val jenisFutsal: String,
    @SerializedName("jenis_basket") val jenisBasket: String,
    @SerializedName("fasilitas_wifi") val fasilitasWifi: String,
    @SerializedName("fasilitas_parkir_motor") val fasilitasParkirMotor: String,
    @SerializedName("fasilitas_parkir_mobil") val fasilitasParkirMobil: String,
    @SerializedName("fasilitas_wc") val fasilitasWC: String,
    @SerializedName("fasilitas_kantin") val fasilitasKantin: String,
    @SerializedName("fasilitas_mushola") val fasilitasMushola: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(no)
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(lat)
        parcel.writeString(lng)
        parcel.writeString(vicinity)
        parcel.writeString(kecamatan)
        parcel.writeString(kabupaten)
        parcel.writeString(rating)
        parcel.writeString(userRatingsTotal)
        parcel.writeString(price)
        parcel.writeString(jenisSepakbola)
        parcel.writeString(jenisBadminton)
        parcel.writeString(jenisTenis)
        parcel.writeString(jenisVoli)
        parcel.writeString(jenisFutsal)
        parcel.writeString(jenisBasket)
        parcel.writeString(fasilitasWifi)
        parcel.writeString(fasilitasParkirMotor)
        parcel.writeString(fasilitasParkirMobil)
        parcel.writeString(fasilitasWC)
        parcel.writeString(fasilitasKantin)
        parcel.writeString(fasilitasMushola)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainAdapterRow> {
        override fun createFromParcel(parcel: Parcel): MainAdapterRow {
            return MainAdapterRow(parcel)
        }

        override fun newArray(size: Int): Array<MainAdapterRow?> {
            return arrayOfNulls(size)
        }
    }
}