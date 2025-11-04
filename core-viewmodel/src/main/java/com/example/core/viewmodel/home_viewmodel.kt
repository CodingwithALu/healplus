package com.example.core.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.banners.BannersModel
import com.example.core.network.retrofitclients.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewmodel: ViewModel() {
    private val _banner = MutableLiveData<List<BannersModel>>()
    val banners: LiveData<List<BannersModel>> = _banner
    fun loadBanners() {
        RetrofitClient.instance.getBanners().enqueue(object : Callback<List<BannersModel>> {
            override fun onResponse(
                call: Call<List<BannersModel>>,
                response: Response<List<BannersModel>>
            ) {
                if (response.isSuccessful) {

                    _banner.value = response.body() ?: emptyList()
                    _banner.value?.forEachIndexed { index, banner ->
                    }
                } else {
                    Log.e("API_ERROR", "Lỗi Response Code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<BannersModel>>, t: Throwable) {
                Log.e("API_ERROR", "Lỗi khi gọi API: ${t.message}")
            }
        })
    }
}