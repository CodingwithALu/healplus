package com.example.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.products.ReviewItem
import com.example.core.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private  val reviewRepository: ReviewRepository
): ViewModel(){
    // createReview
    fun createReview(review: ReviewItem, idp: String){
        viewModelScope.launch {
            reviewRepository.createReview(review, idp)
        }
    }
}