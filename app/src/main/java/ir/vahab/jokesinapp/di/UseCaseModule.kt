package ir.vahab.jokesinapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vahab.jokesinapp.domain.repository.JokeRepository
import ir.vahab.jokesinapp.domain.usecase.GetJokesUseCase
import ir.vahab.jokesinapp.domain.usecase.GetJokesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetJokeUseCase(jokeRepository: JokeRepository): GetJokesUseCase =
        GetJokesUseCaseImpl(jokeRepository = jokeRepository)
}