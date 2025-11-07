package com.example.healplus.di
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.ApiConstants
import com.example.core.repository.CollectionRepository
import com.example.core.repository.HomeRepository
import com.example.core.repository.OrderRepository
import com.example.core.repository.ProductRepository
import com.example.core.repository.RevenueRepository
import com.example.core.repository.ReviewRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    // add Products
    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepository(apiService)
    }
    // homeRepository
    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository {
        return HomeRepository(apiService)
    }
    @Provides
    @Singleton
    fun provideCollectionRepository(apiService: ApiService): CollectionRepository{
        return CollectionRepository(apiService)
    }
    @Provides
    @Singleton
    fun provideOrderRepository(apiService: ApiService): OrderRepository{
        return OrderRepository(apiService)
    }
    @Provides
    @Singleton
    fun provideRevenueRepository(apiService: ApiService): RevenueRepository{
        return RevenueRepository(apiService)
    }
    @Provides
    @Singleton
    fun provideReviewRepository(apiService: ApiService): ReviewRepository{
        return ReviewRepository(apiService)
    }
}