package com.example.core.repository
import com.example.core.model.api.ApiResponse
import com.example.core.model.products.Thanhphan
import com.example.core.model.products.UnitInfo
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
class ProductRepository(
    private val api: ApiService = RetrofitClient.instance
) {
    // add product
    suspend fun addProducts(
        name: String,
        trademark: String,
        rating: String,
        review: String,
        comment: String,
        expiry: String,
        price: String,
        preparation: String,
        specification: String,
        origin: String,
        manufacturer: String,
        ingredient: String,
        description: String,
        quantity: String,
        dateTime: String,
        congdung: String,
        cachdung: String,
        tacdungphu: String,
        ide: String,
        baoquan: String,
        thanhphan: List<Thanhphan>,
        productImages: List<String>,
        unitNames: List<UnitInfo>
    ): ApiResponse {
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO) {
            val gson = Gson()
            result = api.addProduct(
                name = name,
                trademark = trademark,
                rating = rating,
                review = review,
                sold = comment,
                expiry = expiry,
                price = price,
                preparation = preparation,
                specification = specification,
                origin = origin,
                manufacturer = manufacturer,
                ingredient = ingredient,
                description = description,
                quantity = quantity,
                ide = ide,
                dateTime = dateTime,
                congdung = congdung,
                cachdung = cachdung,
                tacdungphu = tacdungphu,
                baoquan = baoquan,
                productImages = gson.toJson(productImages),
                thanhphan = gson.toJson(thanhphan),
                unitNames = gson.toJson(unitNames)
            )
            delay(1000L)
        }
        return result
    }
}