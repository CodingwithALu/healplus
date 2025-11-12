package com.example.core.model.products.conten

import android.os.Parcel
import android.os.Parcelable

data class ReviewItem(
    val name: String = "",
    val rating: Float = 0f,
    val comment: String = "",
    val date: String = "",
    val ulr: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString() ?: "",
        rating = parcel.readFloat(),
        comment = parcel.readString() ?: "",
        date = parcel.readString() ?: "",
        ulr = parcel.readString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeFloat(rating)
        parcel.writeString(comment)
        parcel.writeString(date)
        parcel.writeString(ulr)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<ReviewItem> {
        override fun createFromParcel(parcel: Parcel): ReviewItem {
            return ReviewItem(parcel)
        }
        override fun newArray(size: Int): Array<ReviewItem?> {
            return arrayOfNulls(size)
        }
        fun empty(): ReviewItem = ReviewItem(
            name = "",
            rating = 0f,
            comment = "",
            date = "",
            ulr = null
        )
    }
    fun toJsonMap(): Map<String, Any?> = mapOf(
        "name" to name,
        "rating" to rating,
        "comment" to comment,
        "date" to date,
        "url" to ulr
    )
}
