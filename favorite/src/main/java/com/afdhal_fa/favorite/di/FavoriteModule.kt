package com.afdhal_fa.favorite.di

import com.afdhal_fa.core.domain.usecase.GameInteractor
import com.afdhal_fa.core.domain.usecase.GamesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class FavoriteModule {

    @Binds
    abstract fun provideTourismUseCase(gameInteractor: GameInteractor): GamesUseCase
}
