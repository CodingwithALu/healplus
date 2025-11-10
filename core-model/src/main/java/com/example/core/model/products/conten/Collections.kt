package com.example.core.model.products.conten

import android.os.Parcel
import android.os.Parcelable

data class IngredientDetail(
    val title: String = "",
    val body: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        title = parcel.readString() ?: "",
        body = parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(body)
    }
    override fun describeContents(): Int {
        return 0
    }
    // map helper for nested serialization
    fun toJsonMap(): Map<String, Any?> {
        return mapOf(
            "title" to title,
            "body" to body
        )
    }
    companion object CREATOR : Parcelable.Creator<IngredientDetail> {
        override fun createFromParcel(parcel: Parcel): IngredientDetail {
            return IngredientDetail(parcel)
        }

        override fun newArray(size: Int): Array<IngredientDetail?> {
            return arrayOfNulls(size)
        }
    }
}
