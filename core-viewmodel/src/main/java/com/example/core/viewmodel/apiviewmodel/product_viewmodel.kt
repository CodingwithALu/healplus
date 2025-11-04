package com.example.core.viewmodel.apiviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.products.Thanhphan
import com.example.core.model.products.UnitInfo
import com.example.core.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel(){
    // add Product
    fun addProductModel(
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
    ) {
        viewModelScope.launch {
            productRepository.addProducts(

            )
        }
    }
}
