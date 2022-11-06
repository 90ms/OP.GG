package com.a90ms.data.di

import com.a90ms.data.api.ApiService
import com.a90ms.data.base.HOST_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkServiceModule {

    @Provides
    @Singleton
    fun provideApiService(
        @Named(HOST_NAME)
        retrofitBuilder: Retrofit.Builder
    ): ApiService = retrofitBuilder.build().create(ApiService::class.java)
}
