package com.automotive.bootcamp.launcher.di

import android.content.Context
import android.content.Intent
import com.automotive.bootcamp.example.data.datasource.TpmsDataSource
import com.automotive.bootcamp.example.data.datasource.TpmsIPCDataSource
import com.automotive.bootcamp.example.data.datasource.TpmsMockDataSource
import com.automotive.bootcamp.example.data.datasource.UserDataSource
import com.automotive.bootcamp.example.data.datasource.UserNetworkMockDataSource
import com.automotive.bootcamp.example.data.datasource.WarningsDataSource
import com.automotive.bootcamp.example.data.datasource.WarningsMockDataSource
import com.automotive.bootcamp.example.data.datasource.service.ITpmsClient
import com.automotive.bootcamp.example.data.datasource.service.TpmsServiceClient
import com.automotive.bootcamp.example.data.repository.ExampleRepositoryImpl
import com.automotive.bootcamp.example.domain.repository.ExampleRepository
import com.automotive.bootcamp.launcher.BuildConfig
import com.automotive.bootcamp.launcher.di.TpmsServiceConstants.TPMS_SERVICE_ACTION
import com.automotive.bootcamp.launcher.di.TpmsServiceConstants.TPMS_SERVICE_PACKAGE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExampleModule {

    @Provides
    @Singleton
    fun provideExampleRepository(
        tpmsDataSource: TpmsDataSource,
        warningsDataSource: WarningsDataSource,
        userDataSource: UserDataSource
    ): ExampleRepository {
        return ExampleRepositoryImpl(
            Dispatchers.Default,
            userDataSource,
            warningsDataSource,
            tpmsDataSource
        )
    }

    @Provides
    @Singleton
    @Named("Mock")
    fun provideTpmsMockDataSource(): TpmsDataSource {
        return TpmsMockDataSource()
    }

    @Provides
    @Singleton
    @Named("IPC")
    fun provideTpmsIPCDataSource(
        serviceClient: ITpmsClient,
        @ApplicationScope scope: CoroutineScope, // inject external scope if needed
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): TpmsDataSource {
        return TpmsIPCDataSource (serviceClient, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideTpmsDataSourceProvider(
        @Named("IPC") ipcDataSource: TpmsDataSource,
        @Named("Mock") mockDataSource: TpmsDataSource,
    ): TpmsDataSource {
        // We can configure to use the mock data source in debug builds and ipcDataSource in release builds
//        return if (BuildConfig.DEBUG) {
//            mockDataSource
//        } else ipcDataSource
        return ipcDataSource
    }

    @Provides
    @Singleton
    fun provideTpmsServiceClient(
        @ApplicationContext context: Context,
        @ApplicationScope scope: CoroutineScope, // inject external scope if needed
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): ITpmsClient {
        val intent = Intent(TPMS_SERVICE_ACTION).apply { `package` = TPMS_SERVICE_PACKAGE }
        return TpmsServiceClient(context, intent, ioDispatcher)
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

object TpmsServiceConstants {
    const val TPMS_SERVICE_ACTION = "com.automotive.bootcamp.example.ipc.action.TPMS_SERVICE"
    const val TPMS_SERVICE_PACKAGE = "com.automotive.bootcamp.example.ipc"
}