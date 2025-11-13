package com.example.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.products.ProductsModel
import com.example.core.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){
    private val _product = MutableStateFlow(ProductsModel.empty())
    val product: StateFlow<ProductsModel> = _product
    var isLoading by mutableStateOf(false)
    // add Product
    fun addProductModel(productsModel: ProductsModel, dateTime: LocalDate) {
        viewModelScope.launch {
            productRepository.addProducts(productsModel, dateTime)
        }
    }
    fun fetchProductFromId(id: String){
        viewModelScope.launch {
         isLoading = true
         try {
             _product.value = productRepository.fetchProductFromId(id)
         }catch (e: Exception){
             throw IllegalArgumentException(e.message)
         } finally {
             isLoading = false
         }
        }
    }
}
