package com.characters.rickandmorty.model


import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable {

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}