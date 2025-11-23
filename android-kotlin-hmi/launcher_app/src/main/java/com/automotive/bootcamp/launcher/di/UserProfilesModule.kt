package com.automotive.bootcamp.launcher.di

import com.automotive.bootcamp.userprofiles.data.datasource.ConfigurationDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.ConfigurationMockDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.UserDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.UserNetworkMockDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.WarningsDataSource
import com.automotive.bootcamp.userprofiles.data.datasource.WarningsMockDataSource
import com.automotive.bootcamp.userprofiles.domain.repository.UserProfilesRepository
import com.automotive.bootcamp.userprofiles.data.repository.UserProfilesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserProfilesModule {

    @Provides
    @Singleton
    fun provideUserProfilesRepository(
        configurationDataSource: ConfigurationDataSource,
        warningsDataSource: WarningsDataSource,
        userDataSource: UserDataSource
    ): UserProfilesRepository {
        return UserProfilesRepositoryImpl(
            Dispatchers.Default,
            userDataSource,
            warningsDataSource,
            configurationDataSource
        )
    }

    @Provides
    @Singleton
    fun provideConfigurationDataSource(): ConfigurationDataSource {
        return ConfigurationMockDataSource()
    }

    @Provides
    @Singleton
    fun provideWarningsDataSource(): WarningsDataSource {
        return WarningsMockDataSource()
    }

    @Provides
    @Singleton
    fun provideUserDataSource(): UserDataSource {
        return UserNetworkMockDataSource()
    }
}
