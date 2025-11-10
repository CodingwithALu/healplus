package com.example.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.banners.BannersModel
import com.example.core.model.categories.CategoryModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    private val _banner = MutableStateFlow<List<BannersModel>>(emptyList())
    val banners: StateFlow<List<BannersModel>> = _banner
    private val _category = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categories: StateFlow<List<CategoryModel>> = _category
    private val _ingredient = MutableStateFlow<List<IngredientsModel>>(emptyList())
    val ingredient: StateFlow<List<IngredientsModel>> = _ingredient
    private val _recommended = MutableStateFlow<List<ProductsModel>>(emptyList())
    val recommended: StateFlow<List<ProductsModel>> = _recommended
    private val _searchProduct = MutableStateFlow<List<ProductsModel>>(emptyList())
    val searchProduct: StateFlow<List<ProductsModel>> = _searchProduct
    var isLoading by  mutableStateOf(false)
        private set
    init {
        fetchInit()
    }
    fun fetchInit() {
        viewModelScope.launch {
            isLoading = true
            try {
                withContext(NonCancellable){
                    val result = async { homeRepository.fetchBanner() }.await()
                    val resultCate = async { homeRepository.fetchCategory() }.await()
                    val resultIngredient = async { homeRepository.fetchIngredient() }.await()
                    val resultRecomment = async { homeRepository.fetchRecommended() }.await()
                    _banner.value = result
                    _category.value = resultCate
                    _ingredient.value = resultIngredient
                    _recommended.value = resultRecomment
                }
            } catch (e: Exception){
                isLoading = false
            }finally {
                isLoading = false
            }
        }
    }
    fun searchProduct(search: String){
        viewModelScope.launch {
            isLoading = true
            try {
                val result = async { homeRepository.searchProduct(search) }
                _searchProduct.value = result.await() as MutableList<ProductsModel>
            }catch (e: Exception){
                throw IllegalArgumentException(e.message)
            }finally {
                isLoading = false
            }

        }
    }
}