package com.a90ms.opgg.di

import com.a90ms.opgg.base.LoadingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object AppModule {

    @Provides
    fun provideLoading(): LoadingState = LoadingState()
}
