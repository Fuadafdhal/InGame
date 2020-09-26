package com.afdhal_fa.core.di

import android.content.Context
import androidx.room.Room
import com.afdhal_fa.core.data.local.room.GamesDao
import com.afdhal_fa.core.data.local.room.GamesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GamesDatabase = Room.databaseBuilder(
        context,
        GamesDatabase::class.java, "InGame.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: GamesDatabase): GamesDao = database.gamesDao()
}