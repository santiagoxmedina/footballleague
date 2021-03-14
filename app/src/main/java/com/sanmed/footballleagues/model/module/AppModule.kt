package com.sanmed.footballleagues.model.module

import com.sanmed.footballleagues.model.repository.IFootballRepository
import com.sanmed.footballleagues.model.datasource.ILocalDataSource
import com.sanmed.footballleagues.model.datasource.IRemoteDataSource
import com.sanmed.footballleagues.model.datasource.LocalDataSource
import com.sanmed.footballleagues.model.datasource.TheSportsDbDataSource
import com.sanmed.footballleagues.model.repository.FootballRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun bindILocalDataSource(iLocalDataSource: LocalDataSource): ILocalDataSource

    @Singleton
    @Binds
    abstract fun bindIItemsRepository(repository: FootballRepository): IFootballRepository

    @Singleton
    @Binds
    abstract fun bindIRemoteDataSource(iRemoteDataSource:TheSportsDbDataSource):IRemoteDataSource
}