package com.example.core.viewmodel.apiviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.products.ProductsModel
import com.example.core.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){
    // add Product
    fun addProductModel(productsModel: ProductsModel, dateTime: LocalDate) {
        viewModelScope.launch {
            productRepository.addProducts(productsModel, dateTime)
        }
    }
}
