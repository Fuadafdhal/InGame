package com.dicoding.tourismapp.core.di

import com.afdhal_fa.core.data.GamesRepository
import com.afdhal_fa.core.domain.repository.IGamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(gamesRepository: GamesRepository): IGamesRepository

}