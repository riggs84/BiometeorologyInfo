package com.example.testcomposeapp.di

import android.content.Context
import com.example.testcomposeapp.data.DataStoreManager
import com.example.testcomposeapp.data.HtmlParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesHtmlParser(): HtmlParser = HtmlParser()

    @Provides
    @Singleton
    fun providesDataStoreManager(@ApplicationContext ctx: Context): DataStoreManager =
        DataStoreManager(ctx)
}