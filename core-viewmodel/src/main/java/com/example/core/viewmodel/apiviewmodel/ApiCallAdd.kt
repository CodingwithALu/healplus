package com.example.core.viewmodel.apiviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ApiCallAdd : ViewModel() {
    private val _submitReviewStatus = MutableStateFlow<Boolean?>(null)
    val submitReviewStatus: StateFlow<Boolean?> = _submitReviewStatus
    fun resetSubmitReviewStatus() {
        _submitReviewStatus.value = null
    }
}

