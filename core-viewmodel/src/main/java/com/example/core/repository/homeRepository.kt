package com.example.core.repository

import com.example.core.model.banners.BannersModel
import com.example.core.model.categories.CategoryModel
import com.example.core.model.elements.ElementsModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(
    private val api: ApiService = RetrofitClient.instance
) {
    // fetch banner
    suspend fun fetchBanner(): List<BannersModel> {
        return withContext(Dispatchers.IO) {
            api.getBanners()
        }
    }
    // fetch category
    suspend fun fetchCategory(): List<CategoryModel> {
        return withContext(Dispatchers.IO) {
            api.getCategories()
        }
    }
    // fetch IngredientCount
    suspend fun fetchIngredient(): List<IngredientsModel> {
        return withContext(Dispatchers.IO){
            api.getIngredient()
        }
    }
    // fetch element
    suspend fun fetchElement(): List<ElementsModel>{
        return withContext(Dispatchers.IO){
            api.getElement()
        }
    }
    // fetch Recommended
    suspend fun fetchRecommended(show: Int = 1): List<ProductsModel>{
        return withContext(Dispatchers.IO){
            api.getRecommendedProducts(show)
        }
    }
    // search products
    suspend fun searchProduct(search: String): List<ProductsModel>{
        var result = emptyList<ProductsModel>()
        withContext(Dispatchers.IO){
            result = api.getSearchProduct(search)
        }
        return result
    }
}