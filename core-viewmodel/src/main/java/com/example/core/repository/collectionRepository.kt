package com.example.core.repository

import com.example.core.model.api.ApiResponse
import com.example.core.model.categories.CategoryModel
import com.example.core.model.elements.ElementsModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectionRepository(
    private val api: ApiService = RetrofitClient.instance
) {
    // category

    suspend fun fetchProductByElement(ide: String): List<ProductsModel>{
        var result = emptyList<ProductsModel>()
        withContext(Dispatchers.IO){
            result = api.getProductsByElement(ide)
        }
        return result
    }
    suspend fun fetchProductByIngredient(id: String): List<ProductsModel>{
        var result = emptyList<ProductsModel>()
        withContext(Dispatchers.IO){
            result = api.getProductsByIngredient(id)
        }
        return result
    }
    suspend fun fetchProductByCategory(id: String): List<ProductsModel>{
        var result = emptyList<ProductsModel>()
        withContext(Dispatchers.IO){
            result = api.getProductsByCategory(id)
        }
        return result
    }
    // ingredient
    suspend fun fetchIngredientByCategory(id: String): List<IngredientsModel>{
        var result = emptyList<IngredientsModel>()
        withContext(Dispatchers.IO){
            result = api.getIngredientByCategory(id)
        }
        return result
    }
    suspend fun createIngredient(ingredientsModel: IngredientsModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.addIngredient(
                ingredientsModel.title,
                ingredientsModel.url,
                ingredientsModel.idc
            )
        }
        return result
    }
    suspend fun updateIngredient(ingredientsModel: IngredientsModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.updateIngredient(
                ingredientsModel.iding,
                ingredientsModel.title,
                ingredientsModel.url,
                ingredientsModel.idc
            )
        }
        return result
    }
    // element
    suspend fun fetchElementByIngredient(id: String): List<ElementsModel>{
        var result = emptyList<ElementsModel>()
        withContext(Dispatchers.IO){
            result = api.getElementByIngredient(id)
        }
        return result
    }
    suspend fun createElement(quantity: Int, elementsModel: ElementsModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.addElement(
                elementsModel.title,
                elementsModel.url,
                quantity.toString(),
                elementsModel.iding
            )
        }
        return result
    }
    suspend fun updateElement(quantity: Int, elementsModel: ElementsModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.updateElement(
                elementsModel.ide,
                elementsModel.title,
                elementsModel.url,
                quantity.toString(),
                elementsModel.iding
            )
        }
        return result
    }
    suspend fun deleteElement(id: String): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.deleteElement(id)
        }
        return result
    }
    // category
    suspend fun createCategory(categoryModel: CategoryModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.addCategory(
                categoryModel.title
            )
        }
        return result
    }
    suspend fun updateCategories(categoryModel: CategoryModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.updateCategory(
                categoryModel.idc,
                categoryModel.title
            )
        }
        return result
    }
    suspend fun deleteCategory(id: String): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.deleteCategory(id)
        }
        return result
    }

}