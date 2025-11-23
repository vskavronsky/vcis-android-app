package com.automotive.bootcamp.voicesettings.di

import android.content.Context
import android.content.Intent
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCategoryDataSource
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCategoryIPCDataSource
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCategoryMockDataSource
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCommandDataSource
import com.automotive.bootcamp.voicesettings.data.datasource.VoiceCommandMockDataSource
import com.automotive.bootcamp.voicesettings.data.datasource.service.IVcisClient
import com.automotive.bootcamp.voicesettings.data.datasource.service.VcisClientService
import com.automotive.bootcamp.voicesettings.data.repository.VoiceSettingsRepositoryImpl
import com.automotive.bootcamp.voicesettings.domain.repository.VoiceSettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VoiceSettingsModule {

    @Provides
    @Singleton
    @Named("Mock")
    fun provideVoiceCategoryMockDataSource(): VoiceCategoryDataSource {
        return VoiceCategoryMockDataSource()
    }

    @Provides
    @Singleton
    @Named("IPC")
    fun provideVoiceCategoryIPCDataSource(
        vcisClientService: IVcisClient
    ): VoiceCategoryDataSource {
        return VoiceCategoryIPCDataSource(vcisClientService, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideVcisClientService(
        @ApplicationContext context: Context
    ): IVcisClient {
        val intent =
            Intent(VcisServiceConstants.ACTION).apply { `package` = VcisServiceConstants.PACKAGE }
        return VcisClientService(context, intent, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideVoiceCommandDataSource(): VoiceCommandDataSource {
        return VoiceCommandMockDataSource()
    }

    @Provides
    @Singleton
    fun provideVoiceSettingsRepository(
        @Named("IPC") voiceCategoryDataSource: VoiceCategoryDataSource,
        voiceCommandDataSource: VoiceCommandDataSource
    ): VoiceSettingsRepository {
        return VoiceSettingsRepositoryImpl(
            Dispatchers.Default,
            voiceCategoryDataSource,
            voiceCommandDataSource
        )
    }
}

object VcisServiceConstants {
    const val ACTION = "com.automotive.bootcamp.voicesettings.ipc.action.VCIS_SERVICE"
    const val PACKAGE = "com.automotive.bootcamp.voicesettings.ipc"
}
