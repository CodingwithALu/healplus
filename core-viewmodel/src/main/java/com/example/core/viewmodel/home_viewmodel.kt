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
import com.example.core.model.users.UserModel
import com.example.core.repository.AuthRepository
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
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _user = MutableStateFlow(UserModel.empty())
    val user: StateFlow<UserModel> = _user
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
    var isLoading by mutableStateOf(false)
        private set

    init {
        fetchInit()
    }

    fun fetchInit() {
        isLoading = true
        viewModelScope.launch {
            try {
                withContext(NonCancellable) {
                    // User
                    _banner.value = homeRepository.fetchBanner()
                    _category.value = homeRepository.fetchCategory()
                    _user.value = authRepository.fetchUserFromData()
                    _recommended.value = homeRepository.fetchRecommended()
                    _ingredient.value = homeRepository.fetchIngredient()
                }
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
                isLoading = false
            }
        }
    }

    fun searchProduct(search: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val result = async { homeRepository.searchProduct(search) }
                _searchProduct.value = result.await() as MutableList<ProductsModel>
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
                isLoading = false
            }

        }
    }
}