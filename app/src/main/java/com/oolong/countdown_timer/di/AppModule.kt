package com.oolong.countdown_timer.di

import android.content.Context
import com.oolong.countdown_timer.data.repository.UserPreferencesRepositoryImpl
import com.oolong.countdown_timer.domain.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserPreferences(
        @ApplicationContext app: Context
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(app)
}