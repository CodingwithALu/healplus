package com.example.core.network.apis
import com.example.core.model.Oder.OrderModel
import com.example.core.model.api.ApiResponse
import com.example.core.model.banners.BannersModel
import com.example.core.model.categories.CategoryModel
import com.example.core.model.elements.ElementsModel
import com.example.core.model.ingredients.IngredientsModel
import com.example.core.model.products.ProductsModel
import com.example.core.model.revenue.RevenueResponse
import com.example.core.model.users.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

interface ApiService {
    // Todo -- Get DATA
    @GET("get_ingredient_count.php")
    suspend fun getIngredientCount(): List<IngredientsModel>
    @GET("fetchBanner.php")
    suspend fun getBanners(): List<BannersModel>
    @GET("getIngredient.php")
    suspend fun getIngredient(): List<IngredientsModel>
    @GET("get_product_showRecomment.php")
    suspend fun getRecommendedProducts(): List<ProductsModel>
    @GET("fetchCategory.php")
    suspend fun getCategories(): List<CategoryModel>
    @GET("fetchElement.php")
    suspend fun getElement(): List<ElementsModel>
    @GET("route/get/fetch_products_from_category.php")
    suspend fun getProductsByCategory(@Query("idc") idc: String): List<ProductsModel>
    @GET("get_products_by_ingredient.php")
    suspend fun getProductsByIngredient(@Query("id") id: String): List<ProductsModel>
    @GET("get_products_by_element.php")
    suspend fun getProductsByElement(@Query("id") id: String): List<ProductsModel>
    @GET("get_ingredient_by_category.php")
    suspend fun getIngredientByCategory(@Query("id") id: String): List<IngredientsModel>
    @GET("get_elements_by_ingredient.php")
    suspend fun getElementByIngredient(@Query("id") id: String): List<ElementsModel>
    @GET("getsearch.php")
    suspend fun getSearchProduct(@Query("search") search: String): List<ProductsModel>
    @GET("get_oder.php")
    suspend fun getOder(): List<OrderModel>

    // user
    @GET("route/get/fetch_user.php")
    suspend fun fetchUserFromData(@Query("id") id: String): UserModel
    @FormUrlEncoded
    @POST("create_user.php")
    suspend fun createUser(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): ApiResponse
    @FormUrlEncoded
    @POST("add_category.php")
    suspend fun addCategory(
        @Field("title") title: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("add_ingrident.php")
    suspend fun addIngredient(
        @Field("title") title: String,
        @Field("url") url: String,
        @Field("idc") idc: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("add_element.php")
    suspend fun addElement(
        @Field("title") title: String,
        @Field("url") url: String,
        @Field("quantity") quantity: String,
        @Field("iding") iding: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_category.php")
    suspend fun updateCategory(
        @Field("idc") idc: String,
        @Field("title") title: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_review.php")
    suspend fun updateReview(
        @Field("idp") idc: String,
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_ingredient.php")
    suspend fun updateIngredient(
        @Field("iding") iding: String,
        @Field("title") title: String,
        @Field("url") url: String,
        @Field("idc") idc: String,
    ): ApiResponse

    @FormUrlEncoded
    @POST("update_element.php")
    suspend fun updateElement(
        @Field("ide") ide: String,
        @Field("title") title: String,
        @Field("url") url: String,
        @Field("quantity") quantity: String,
        @Field("iding") iding: String,
    ): ApiResponse
    @FormUrlEncoded
    @POST("delete_category.php")
    suspend fun deleteCategory(
        @Field("idc") idc: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("deldelete_ingredient.php")
    suspend fun deleteIngnredient(
        @Field("iding") iding: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_element.php")
    suspend fun deleteElement(
        @Field("ide") ide: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("oder.php")
    suspend fun addOder(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("email") email: String,
        @Field("idauth") idauth: String,
        @Field("address") address: String,
        @Field("datetime") datetime: LocalDate,
        @Field("note") note: String,
        @Field("quantity") quantity: Int,
        @Field("sumMoney") sumMoney: Float,
        @Field("status") status: String,
        @Field("detail") detail: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("add_review.php")
    suspend fun addReview(
        @Field("reviewerName") reviewerName: String,
        @Field("rating") rating: Float,
        @Field("comment") comment: String,
        @Field("date") date: String,
        @Field("profileImageUrl") profileImageUrl: String,
        @Field("idp") idp: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_user.php")
    suspend fun upDateUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
        @Field("phone") phone: String,
        @Field("url") url: String,
        @Field("dateBirth") dateBirth: String,
        @Field("idauth") idauth: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_idauth.php")
    suspend fun upDateIdAuth(
        @Field("email") email: String,
        @Field("idauth") idauth: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("add_product.php")
    suspend fun addProduct(
        @Field("name") name: String,
        @Field("trademark") trademark: String,
        @Field("rating") rating: String,
        @Field("review") review: String,
        @Field("sold") sold: String,
        @Field("expiry") expiry: String,
        @Field("preparation") preparation: String,
        @Field("specification") specification: String,
        @Field("origin") origin: String,
        @Field("manufacturer") manufacturer: String,
        @Field("ingredient") ingredient: String,
        @Field("description") description: String,
        @Field("quantity") quantity: String,
        @Field("ide") ide: String,
        @Field("productiondate") dateTime: String,
        @Field("congdung") congdung: String,
        @Field("cachdung") cachdung: String,
        @Field("tacdungphu") tacdungphu: String,
        @Field("baoquan") baoquan: String,
        @Field("productImages") productImages: String,
        @Field("thanhphan") thanhphan: String,
        @Field("unitNames") unitNames: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("update_oder_status.php")
    suspend fun updateOrderStatus(
        @Field("id") orderId: Int,
        @Field("status") status: String
    ): ApiResponse
    @FormUrlEncoded
    @POST("get_oder_by_status.php")
    suspend fun getOrderStatus(
        @Field("status") status: String
    ): List<OrderModel>

    @FormUrlEncoded
    @POST("get_oder_by_user.php")
    suspend fun getOrderByUser(
        @Field("idauth") idUser: String
    ): List<OrderModel>

    @FormUrlEncoded
    @POST("get_oder_by_userstatus.php")
    suspend fun getOrderByStatusByUser(
        @Field("idauth") idauth: String,
        @Field("status") status: String
    ): List<OrderModel>
    
    @GET("revenue_month.php")
    suspend fun revenueMonth(
        @Query("month") month: Int,
        @Query("year") year: Int
    ): RevenueResponse
    @GET("revenue_week.php")
    suspend fun revenueWeek(
        @Query("start_date") start_date: String
    ): RevenueResponse
    @GET("revenue_year.php")
    suspend fun revenueYear(
        @Query("year") year: Int
    ): RevenueResponse
}