package com.example.core.viewmodel.apiviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.categories.CategoryModel
import com.example.core.model.elements.ElementsModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.repository.CollectionRepository
import com.example.core.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    val category: LiveData<MutableList<CategoryModel>> = _category
    private val _ingredient = MutableLiveData<MutableList<IngredientsModel>>()
    val ingredient: LiveData<MutableList<IngredientsModel>> = _ingredient
    private val _element = MutableLiveData<MutableList<ElementsModel>>()
    val element: LiveData<MutableList<ElementsModel>> = _element
    private val _currentCall = MutableLiveData<MutableList<ProductsModel>>()
    val currentCall: LiveData<MutableList<ProductsModel>> = _currentCall
    var isLoading by mutableStateOf(false)

    init {
        fetchCategory()
    }

    //fetch category
    fun fetchCategory() {
        viewModelScope.launch {
            try {
                val category = async { homeRepository.fetchCategory() }.await()
                _category.value = category as MutableList<CategoryModel>
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
            }
        }
    }
    fun fetchIngredientAndProductFromCategory(id: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val ingredient =
                    async { collectionRepository.fetchIngredientByCategory(id) }
                val product =
                    async { collectionRepository.fetchProductByCategory(id) }
                _ingredient.value = ingredient.await() as MutableList<IngredientsModel>
                _currentCall.value = product.await() as MutableList<ProductsModel>
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchElementAndProductFromIngredient(id: String) {
        viewModelScope.launch {
            isLoading = true
            try {

                val element = async { collectionRepository.fetchElementByIngredient(id) }
                val product = async { collectionRepository.fetchProductByIngredient(id) }
                _element.value = element.await() as MutableList<ElementsModel>
                _currentCall.value = product.await() as MutableList<ProductsModel>
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
                isLoading = false
            }
        }
    }
    fun fetchProductByElement(id: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val product = async { collectionRepository.fetchProductByElement(id) }
                _currentCall.value = product.await() as MutableList<ProductsModel>
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
                isLoading = false
            }
        }
    }
}