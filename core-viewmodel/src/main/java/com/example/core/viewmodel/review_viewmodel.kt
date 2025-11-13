package com.example.core.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.products.conten.ReviewItem
import com.example.core.repository.AuthRepository
import com.example.core.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private  val reviewRepository: ReviewRepository,
    private val authRepository: AuthRepository
): ViewModel(){
    private val _uid = MutableStateFlow("")
    val uid: StateFlow<String> = _uid
    init {
        fetchId()
    }
    fun fetchId(){
        _uid.value = authRepository.fetchIdAuth()
    }
    // createReview
    fun createReview(review: ReviewItem){
        Log.d("Review", "${review.idUser},${review.idp} ")
        viewModelScope.launch {
            reviewRepository.createReview(review)
        }
    }
    fun updateReview(id: String){
        viewModelScope.launch {
            reviewRepository.updateReview(id)
        }
    }
}