package com.stargazer.bookexplorer.di

import com.stargazer.bookexplorer.data.BookRepository
import dagger.Module; import dagger.Provides
import dagger.hilt.InstallIn; import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides @Singleton fun provideRepo() = BookRepository()
}