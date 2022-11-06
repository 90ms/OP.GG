package com.a90ms.data.di

import com.a90ms.data.BuildConfig
import com.a90ms.data.base.DEFAULT_TIME_OUT
import com.a90ms.data.base.HOST_NAME
import com.a90ms.data.base.HOST_URL
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkCoreModule {

    @Provides
    @Named(HOST_NAME)
    fun provideHostName() = HOST_NAME

    @Provides
    @Singleton
    fun provideObjMatter(): ObjectMapper = ObjectMapper().apply {
        registerKotlinModule()
        registerModule(SimpleModule())
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(objectMapper: ObjectMapper): JacksonConverterFactory =
        JacksonConverterFactory.create(objectMapper)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    @Named(HOST_NAME)
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                Interceptor {
                    it.proceed(it.request().newBuilder().build())
                }
            )
            .addNetworkInterceptor(httpLoggingInterceptor)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        return if (BuildConfig.DEBUG) {
            okHttpClient.build()
            okHttpClient.addNetworkInterceptor(StethoInterceptor()).build()
        } else {
            okHttpClient.build()
        }
    }

    @Provides
    @Singleton
    @Named(HOST_NAME)
    fun provideRetrofitBuilder(
        jacksonConverterFactory: JacksonConverterFactory,
        @Named(HOST_NAME) okHttpClient: OkHttpClient,
    ): Retrofit.Builder = Retrofit
        .Builder()
        .baseUrl(HOST_URL)
        .client(okHttpClient)
        .addConverterFactory(jacksonConverterFactory)
}
