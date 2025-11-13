package com.example.core.model.products.conten

import android.os.Parcel
import android.os.Parcelable

data class ReviewItem(
    val idp: String? = "",
    val rating: Float = 0f,
    val comment: String = "",
    val date: String = "",
    val idUser: String = "",
    val userName: String? = "",
    val userAvatar: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        idp = parcel.readString() ?: "",
        rating = parcel.readFloat(),
        comment = parcel.readString() ?: "",
        date = parcel.readString() ?: "",
        idUser = parcel.readString() ?: "",
        userName = parcel.readString() ?: "",
        userAvatar = parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idp)
        parcel.writeFloat(rating)
        parcel.writeString(comment)
        parcel.writeString(date)
        parcel.writeString(idUser)
        parcel.writeString(userName)
        parcel.writeString(userAvatar)
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
            idp = "",
            rating = 0f,
            comment = "",
            date = "",
            idUser = "",
            userName = "",
            userAvatar = ""
        )
    }
    fun toJsonMap(): Map<String, Any?> = mapOf(
        "idp" to idp,
        "rating" to rating,
        "comment" to comment,
        "date" to date,
        "id_user" to idUser
    )
}
