package com.example.core.repository
import com.example.core.model.api.ApiResponse
import com.example.core.model.products.ProductsModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.LocalDate

class ProductRepository(
    private val api: ApiService = RetrofitClient.instance
) {
    // add product
    suspend fun addProducts(product: ProductsModel, dateTime: LocalDate): ApiResponse {
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO) {
            val gson = Gson()
            result = api.addProduct(
                name = product.name,
                trademark = product.trademark,
                rating = product.rating.toString(),
                review = product.review.toString(),
                sold = product.sold.toString(),
                expiry = product.expiry,
                price = product.price.toString(),
                preparation = product.preparation,
                specification = product.specification,
                origin = product.origin,
                manufacturer = product.manufacturer,
                ingredient = product.ingredient,
                description = product.description,
                quantity = product.quantity.toString(),
                ide = product.ide,
                dateTime = dateTime.toString(),
                congdung = product.congdung,
                cachdung = product.cachdung,
                tacdungphu = product.tacdungphu,
                baoquan = product.baoquan,
                productImages = gson.toJson(product.product_images),
                thanhphan = gson.toJson(product.ingredients),
                unitNames = gson.toJson(product.unit_names)
            )
            delay(1000L)
        }
        return result
    }
}