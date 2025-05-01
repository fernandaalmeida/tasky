package com.falmeida.tasky.core.network.di

import com.falmeida.tasky.core.network.auth.AuthApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import com.falmeida.tasky.core.network.TaskyApi
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class AuthNetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = false
        encodeDefaults = true
    }


    @Provides
    @Singleton
    fun provideAuthApi(
        json: Json,
        okHttpClient: OkHttpClient
    ): AuthApi = Retrofit.Builder()
        .baseUrl(TaskyApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(AuthApi::class.java)
}