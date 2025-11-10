package com.example.core.model.products.conten

import android.os.Parcel
import android.os.Parcelable

data class UnitInfo(
    val name: String = "",
    val price: Int?
):Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString() ?: "",
        price = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(price!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UnitInfo> {
        override fun createFromParcel(parcel: Parcel): UnitInfo {
            return UnitInfo(parcel)
        }

        override fun newArray(size: Int): Array<UnitInfo?> {
            return arrayOfNulls(size)
        }

        fun empty(): UnitInfo = UnitInfo(
            name = "",
            price = 0,
        )
    }

    fun toJsonMap(): Map<String, Any?> = mapOf(
        "name" to name,
        "price" to price
    )
}
