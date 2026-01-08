package com.example.core.model.categories

import com.example.core.model.ingredients.IngredientsModel

data class CategoryModel(
    val idc: String = "",
    val title: String = "",
    val quantity: Int = 0,
    val percentage: Int = 0,
    val createAt: String,
    val updateAt: String,
    val ingredients: List<IngredientsModel>?
)
