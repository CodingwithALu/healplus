package com.example.core.model.products

import android.os.Parcel
import android.os.Parcelable
import com.example.core.model.products.conten.IngredientDetail
import com.example.core.model.products.conten.ReviewItem
import com.example.core.model.products.conten.UnitInfo
import com.google.gson.Gson
data class ProductsModel(
    val idp: String = "",
    val name: String = "",
    val trademark: String = "",
    var rating: Double = 0.0,
    var review: Int = 0,
    var sold: Int = 0,
    val preparation: String = "",
    val origin: String = "",
    val manufacturer: String = "",
    val description: String = "",
    val ide: String = "",
    val productionDate: String = "",
    val expiry: String = "",
    val specification: String = "",
    val ingredient: String = "",
    var quantity: Int = 0,
    val uses: String = "",
    val toUse: String = "",
    val sideEffects: String = "",
    val preserver: String = "",
    var urls: ArrayList<String> = ArrayList(),
    var unitNames: ArrayList<UnitInfo> = ArrayList(),
    var elements: String = "",
    var ingredients: ArrayList<IngredientDetail> = ArrayList(),
    var reviewItems: ArrayList<ReviewItem> = ArrayList(),
) : Parcelable {
    constructor(parcel: Parcel) : this(
        idp = parcel.readString() ?: "",
        name = parcel.readString() ?: "",
        trademark = parcel.readString() ?: "",
        rating = parcel.readDouble(),
        review = parcel.readInt(),
        sold = parcel.readInt(),
        preparation = parcel.readString() ?: "",
        origin = parcel.readString() ?: "",
        manufacturer = parcel.readString() ?: "",
        description = parcel.readString() ?: "",
        ide = parcel.readString() ?: "",
        productionDate = parcel.readString() ?: "",
        expiry = parcel.readString()?: "",
        specification = parcel.readString() ?: "",
        ingredient = parcel.readString() ?: "",
        quantity = parcel.readInt(),
        uses = parcel.readString() ?: "",
        toUse = parcel.readString() ?: "",
        sideEffects = parcel.readString() ?: "",
        preserver = parcel.readString() ?: "",
        urls = parcel.createStringArrayList() ?: arrayListOf(),
        unitNames = parcel.createTypedArrayList(UnitInfo.CREATOR) ?: arrayListOf(),
        elements = parcel.readString() ?: "",
        ingredients = parcel.createTypedArrayList(IngredientDetail.CREATOR) ?: arrayListOf(),
        reviewItems = parcel.createTypedArrayList(ReviewItem.CREATOR) ?: arrayListOf()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idp)
        parcel.writeString(name)
        parcel.writeString(trademark)
        parcel.writeDouble(rating)
        parcel.writeInt(review)
        parcel.writeInt(sold)
        parcel.writeString(preparation)
        parcel.writeString(origin)
        parcel.writeString(manufacturer)
        parcel.writeString(description)
        parcel.writeString(ide)
        parcel.writeString(productionDate)
        parcel.writeString(expiry)
        parcel.writeString(specification)
        parcel.writeString(ingredient)
        parcel.writeInt(quantity)
        parcel.writeString(uses)
        parcel.writeString(toUse)
        parcel.writeString(sideEffects)
        parcel.writeString(preserver)
        parcel.writeStringList(urls)
        parcel.writeTypedList(unitNames)
        parcel.writeString(elements)
        parcel.writeTypedList(ingredients)
        parcel.writeTypedList(reviewItems)
    }
    override fun describeContents(): Int {
        return 0
    }
    fun toJsonMap(): Map<String, Any?> {
        return mapOf(
            "idp" to idp,
            "name" to name,
            "trademark" to trademark,
            "rating" to rating,
            "review" to review,
            "sold" to sold,
            "preparation" to preparation,
            "origin" to origin,
            "manufacturer" to manufacturer,
            "description" to description,
            "ide" to ide,
            "productionDate" to productionDate,
            "expiry" to expiry,
            "specification" to specification,
            "ingredient" to ingredient,
            "quantity" to quantity,
            "uses" to uses,
            "toUse" to toUse,
            "sideEffects" to sideEffects,
            "preserver" to preserver,
            "urls" to urls,
            "unitNames" to unitNames,
            "elements" to elements,
            "ingredients" to ingredients.map { it.toJsonMap() },
            "reviewItems" to reviewItems.map { it.toJsonMap() }
        )
    }
    companion object CREATOR : Parcelable.Creator<ProductsModel> {
        override fun createFromParcel(parcel: Parcel): ProductsModel {
            return ProductsModel(parcel)
        }
        override fun newArray(size: Int): Array<ProductsModel?> {
            return arrayOfNulls(size)
        }
        fun fromJson(json: String): ProductsModel {
            return Gson().fromJson(json, ProductsModel::class.java)
        }
        fun empty(): ProductsModel {
            return ProductsModel()
        }
    }
    fun toJson(): String {
        return Gson().toJson(this)
    }
}
