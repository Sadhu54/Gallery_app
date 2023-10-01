package com.thoughtctl.di

import com.thoughtctl.data.repository.GallerySearchRepositoryImpl
import com.thoughtctl.data.source.api.SearchApi
import com.thoughtctl.domain.repository.GallerySearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Provide an OkHttpClient with custom headers
    @Singleton
    @Provides
    fun getOkHTTPLogging(): OkHttpClient {
        val headersInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Client-ID dd8f138be9f24e9") // Add custom headers here
                .build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(headersInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Provide a Retrofit instance for making API calls
    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/") // Define the base URL for your API
            .client(okHttpClient) // Use the OkHttpClient with custom headers
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON conversion
            .build()
    }

    // Provide an instance of the SearchApi interface
    @Provides
    @Singleton
    fun getGallerySearchAPI(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    // Provide an instance of the GallerySearchRepository interface
    @Provides
    @Singleton
    fun getGallerySearchRepository(api: SearchApi): GallerySearchRepository {
        return GallerySearchRepositoryImpl(api)
    }
}
