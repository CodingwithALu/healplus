package com.example.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.revenue.RevenueResponse
import com.example.core.repository.RevenueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RevenueViewModel @Inject constructor(
    private val revenueRepository: RevenueRepository
): ViewModel(){
    private val _revenueYear = MutableLiveData<RevenueResponse>()
    val revenueYear: LiveData<RevenueResponse> = _revenueYear
    private val _revenueMonth = MutableLiveData<RevenueResponse>()
    val revenueMonth: LiveData<RevenueResponse> = _revenueMonth
    private val _isDataEmptyAfterLoad = MutableLiveData(false)
    val isDataEmptyAfterLoad: LiveData<Boolean> = _isDataEmptyAfterLoad
    // revenue year
    fun revenueYear(year: Int){
        viewModelScope.launch {
            val result = async { revenueRepository.revenueYear(year) }
            _revenueYear.value = result.await()
            _isDataEmptyAfterLoad.value = result.await().revenue.isNotEmpty()
        }
    }
    // revenue month
    fun revenueMonth(month: Int, year: Int){
        viewModelScope.launch {
            val result = async { revenueRepository.revenueMonth(month, year) }
            _revenueMonth.value = result.await()
            _isDataEmptyAfterLoad.value = result.await().revenue.isNotEmpty()
        }
    }
    // revenue Weeks
    fun revenueWeek(startDate: LocalDate){
        viewModelScope.launch {
            val result = async { revenueRepository.revenueWeek(startDate) }
            _revenueYear.value = result.await()
            _isDataEmptyAfterLoad.value = result.await().revenue.isNotEmpty()
        }
    }
}