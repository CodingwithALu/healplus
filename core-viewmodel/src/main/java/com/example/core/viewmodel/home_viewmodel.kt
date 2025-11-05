package com.example.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.banners.BannersModel
import com.example.core.model.categories.CategoryModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    private val _banner = MutableLiveData<List<BannersModel>>()
    val banners: LiveData<List<BannersModel>> = _banner
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    val categories: LiveData<MutableList<CategoryModel>> = _category
    private val _ingredient = MutableLiveData<MutableList<IngredientsModel>>()
    val ingredient: LiveData<MutableList<IngredientsModel>> = _ingredient
    private val _recommended = MutableLiveData<MutableList<ProductsModel>>()
    val recommended: LiveData<MutableList<ProductsModel>> = _recommended
    init {
        fetchInit()
    }
    fun fetchInit() {
        viewModelScope.launch {
            val result = async { homeRepository.fetchBanner() }.await()
            val resultCate = async { homeRepository.fetchCategory() }.await()
            val resultIngredient = async { homeRepository.fetchIngredient() }.await()
            val resultRecomment = async { homeRepository.fetchRecommended() }.await()
            _banner.value = result
            _category.value = resultCate as MutableList<CategoryModel>
            _ingredient.value = resultIngredient as MutableList<IngredientsModel>
            _recommended.value = resultRecomment as MutableList<ProductsModel>
        }
    }
}