package com.example.hexagon_employer_list.di

import com.example.hexagon_employer_list.data.repository.EmployeeRepository
import com.example.hexagon_employer_list.data.repository.EmployeeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesEmployeeRepository(): EmployeeRepository =
        EmployeeRepositoryImpl()
}