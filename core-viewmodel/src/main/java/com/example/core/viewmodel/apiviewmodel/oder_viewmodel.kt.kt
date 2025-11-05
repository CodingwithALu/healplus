package com.example.core.viewmodel.apiviewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.api.ApiResponse
import com.example.core.model.elements.ElementsModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.model.revenue.DetailedOrder
import com.example.core.network.retrofitclients.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel12 : ViewModel() {
    private val _element = MutableLiveData<MutableList<ElementsModel>>()
    val element: LiveData<MutableList<ElementsModel>> = _element
    private var currentCall: Call<List<ProductsModel>>? = null
    private val _historyWeek = MutableLiveData<List<DetailedOrder>>(emptyList())
    val historyWeek: LiveData<List<DetailedOrder>> = _historyWeek
    private val _historyMonth = MutableLiveData<List<DetailedOrder>>(emptyList())
    val historyMonth: LiveData<List<DetailedOrder>> = _historyMonth
    private val _historyYear = MutableLiveData<List<DetailedOrder>>(emptyList())
    val historyYear: LiveData<List<DetailedOrder>> = _historyYear

    fun loadProductBySearch(search: String) {
        currentCall?.cancel()
        currentCall = RetrofitClient.instance.getSearchProduct(search)
        currentCall?.enqueue(object : Callback<List<ProductsModel>> {
            override fun onResponse(
                call: Call<List<ProductsModel>>,
                response: Response<List<ProductsModel>>
            ) {
                if (response.isSuccessful) {
                    val productList = response.body()?.toMutableList() ?: mutableListOf()
                    _recommended.value = productList
                } else {
                    Log.e(
                        "API_ERROR1",
                        "Lỗi Response Code: ${response.code()} - ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                Log.e("API_ERROR1", "Lỗi khi gọi API: ${t.message}")
            }
        })
    }

    fun loadProductByCategory(idc: String) {
        RetrofitClient.instance.getProductsByCategory(idc)
            .enqueue(object : Callback<List<ProductsModel>> {
                override fun onResponse(
                    call: Call<List<ProductsModel>>,
                    response: Response<List<ProductsModel>>
                ) {
                    if (response.isSuccessful) {
                        val productList = response.body()?.toMutableList() ?: mutableListOf()
                        _recommended.value = productList
                    } else {
                        Log.e("API_ERROR", "Lỗi Response Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                    Log.e("API_ERROR", "Lỗi khi gọi API: ${t.message}")
                }
            })
    }

    fun loadProductByIngredient(iding: String) {
        Log.d("API_Ingredient", "Star")
        RetrofitClient.instance.getProductsByIngredient(iding)
            .enqueue(object : Callback<List<ProductsModel>> {
                override fun onResponse(
                    call: Call<List<ProductsModel>>,
                    response: Response<List<ProductsModel>>
                ) {
                    if (response.isSuccessful) {
                        val productsList = response.body()?.toMutableList() ?: mutableListOf()
                        _recommended.value = productsList

                    } else {
                        Log.e("API_ERROR", "Lỗi Response Code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                    Log.e("API_ERROR", "Yêu cầu mạng thất bại: ${t.message}", t)
                }
            })
    }

    fun loadIngredientByCategory(idc: String) {
        RetrofitClient.instance.getIngredientByCategory(idc).enqueue(object :
            Callback<List<IngredientsModel>> {
            override fun onResponse(
                call: Call<List<IngredientsModel>>,
                response: Response<List<IngredientsModel>>
            ) {
                if (response.isSuccessful) {
                    val ingredientsList = response.body()?.toMutableList() ?: mutableListOf()
                    _ingredient.value = ingredientsList
                } else {
                    Log.d("API_Ingredient", "Loi Renpose : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<IngredientsModel>>, t: Throwable) {
                Log.e("API_ERROR", "Lỗi khi gọi API: ${t.message}")
            }
        })
    }

    fun loadElementByIngredient(iding: String) {
        RetrofitClient.instance.getElementByIngredient(iding)
            .enqueue(object : Callback<List<ElementsModel>> {
                override fun onResponse(
                    call: Call<List<ElementsModel>>,
                    response: Response<List<ElementsModel>>
                ) {
                    if (response.isSuccessful) {
                        val ElementsList = response.body()?.toMutableList() ?: mutableListOf()
                        _element.value = ElementsList
                    } else {
                        Log.d("API_Elment", "Loi elemnet: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<ElementsModel>>, t: Throwable) {
                    Log.e("API_ERROR", "Lỗi khi gọi API: ${t.message}")
                }

            })
    }

    fun loadProductByElement(ide: String) {
        RetrofitClient.instance.getProductsByElement(ide)
            .enqueue(object : Callback<List<ProductsModel>> {
                override fun onResponse(
                    call: Call<List<ProductsModel>>,
                    response: Response<List<ProductsModel>>
                ) {
                    if (response.isSuccessful) {
                        val productsList = response.body()?.toMutableList() ?: mutableListOf()
                        _recommended.value = productsList
                    } else {
                        Log.d("API_Element", "Lỗi response code: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<ProductsModel>>, t: Throwable) {
                    Log.e("API_ERROR", "Lỗi khi gọi API: ${t.message}")
                }

            })
    }

    fun addIngredient(
        title: String,
        url: String,
        idc: String,
        onResult: (ApiResponse) -> Unit
    ) {
        Log.d("AddCategory", "Đang gửi request với title: $title")
        val call = RetrofitClient.instance.addIngredient(title, url, idc)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("AddCategory", "Phản hồi thành công: $it")
                        onResult(it)
                        Log.e("AddCategory", "Phản hồi rỗng!")
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("AddCategory", "Lỗi kết nối: ${t.message}")
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun addElment(
        title: String,
        url: String,
        quantity: String,
        iding: String,
        onResult: (ApiResponse) -> Unit
    ) {
        Log.d("AddCategory", "Đang gửi request với title: $title")
        val call = RetrofitClient.instance.addElement(title, url, quantity, iding)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("AddCategory", "Phản hồi thành công: $it")
                        onResult(it)
                        Log.e("AddCategory", "Phản hồi rỗng!")
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("AddCategory", "Lỗi kết nối: ${t.message}")
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun updateIdAuth(email: String, idauth: String) {
        val call = RetrofitClient.instance.upDateIdAuth(email, idauth)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.d("AddCategory", "Cap nhat thanh cong")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("AddCategory", "Lỗi kết nối: ${t.message}")
            }
        })
    }

    fun updateUser(
        name: String,
        email: String,
        gender: String,
        phone: String,
        url: String,
        dateBirth: String,
        idauth: String
    ) {
        val call = RetrofitClient.instance.upDateUser(
            name,
            email,
            gender,
            phone,
            url,
            dateBirth,
            idauth
        )
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.d("AddCategory", "Cap nhat thanh cong")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("AddCategory", "Lỗi kết nối: ${t.message}")
            }
        })
    }
    fun updateCategory(idc: String, title: String, onResult: (ApiResponse) -> Unit) {
        val call = RetrofitClient.instance.updateCategory(idc, title)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun updateElement(
        ide: String,
        title: String,
        url: String,
        quantity: String,
        iding: String,
        onResult: (ApiResponse) -> Unit
    ) {
        val call = RetrofitClient.instance.updateElement(ide, title, url, quantity, iding)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun updateIngredient(
        iding: String,
        title: String,
        url: String,
        idc: String,
        onResult: (ApiResponse) -> Unit
    ) {
        val call = RetrofitClient.instance.updateIngredient(iding, title, url, idc)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun deleteCategory(idc: String, onResult: (ApiResponse) -> Unit) {
        val call = RetrofitClient.instance.deleteCategory(idc)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun deleteIngredient(iding: String, onResult: (ApiResponse) -> Unit) {
        val call = RetrofitClient.instance.deleteIngnredient(iding)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun deleteElement(ide: String, onResult: (ApiResponse) -> Unit) {
        val call = RetrofitClient.instance.deleteElement(ide)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onResult(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(ApiResponse(false, "Lỗi kết nối!"))
            }
        })
    }

    fun upDateReview(idp: String) {
        val call = RetrofitClient.instance.updateReview(idp)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.e("AddCategory", "Thanh cong")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("AddCategory", "Lỗi kết nối: ${t.message}")
            }
        })
    }

}