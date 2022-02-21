package ir.vahab.jokesinapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vahab.jokesinapp.data.local.AppDB
import ir.vahab.jokesinapp.data.remote.AppApi
import ir.vahab.jokesinapp.data.repository.JokeRepositoryImpl
import ir.vahab.jokesinapp.domain.repository.JokeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideJokeRepository(
        api: AppApi,
        db: AppDB
    ): JokeRepository = JokeRepositoryImpl(api, db)
}