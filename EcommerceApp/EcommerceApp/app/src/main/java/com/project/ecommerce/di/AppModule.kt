package com.project.ecommerce.di

import com.project.ecommerce.data.datasource.DataSource
import com.project.ecommerce.data.repo.AuthRepository
import com.project.ecommerce.retrofit.ApiUtils
import com.project.ecommerce.retrofit.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(dataSource: DataSource): AuthRepository {
        return AuthRepository(dataSource)
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(dao: Dao): DataSource {
        return DataSource(dao)
    }

    @Provides
    @Singleton
    fun provideAuthDao(): Dao {
        return ApiUtils.getAuthDao()
    }





}