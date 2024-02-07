package com.test.country.di

import com.test.country.data.network.EndPoints
import com.test.country.data.repository.CountryListRepositoryImpl
import com.test.country.domain.repository.CountryListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesCountryListRepository(endPoints: EndPoints): CountryListRepository {
        return CountryListRepositoryImpl(endPoints)
    }
}