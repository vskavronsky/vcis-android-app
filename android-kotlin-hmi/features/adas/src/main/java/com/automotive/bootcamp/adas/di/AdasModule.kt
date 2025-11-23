package com.automotive.bootcamp.adas.di

import com.automotive.bootcamp.adas.data.datasource.AccMockDataSource
import com.automotive.bootcamp.adas.data.datasource.AdasDataSource
import com.automotive.bootcamp.adas.data.datasource.AdasDataSourceImpl
import com.automotive.bootcamp.adas.data.datasource.AdasFeatureDataSource
import com.automotive.bootcamp.adas.data.datasource.BlindSpotMockDataSource
import com.automotive.bootcamp.adas.data.datasource.CollisionWarningMockDataSource
import com.automotive.bootcamp.adas.data.datasource.LaneAssistMockDataSource
import com.automotive.bootcamp.adas.data.repository.AdasRepositoryImpl
import com.automotive.bootcamp.adas.domain.model.AdasFeatureType
import com.automotive.bootcamp.adas.domain.repository.AdasRepository
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdasModule {
    @MapKey
    annotation class AdasFeatureKey(val value: AdasFeatureType)

    @Provides
    @IntoMap
    @AdasFeatureKey(AdasFeatureType.ADAPTIVE_CRUISE_CONTROL)
    fun provideAccDataSource(): AdasFeatureDataSource {
        return AccMockDataSource()
    }

    @Provides
    @IntoMap
    @AdasFeatureKey(AdasFeatureType.LANE_KEEPING_ASSIST)
    fun provideLaneAssistDataSource(): AdasFeatureDataSource {
        return LaneAssistMockDataSource()
    }

    @Provides
    @IntoMap
    @AdasFeatureKey(AdasFeatureType.BLIND_SPOT_MONITORING)
    fun provideBlindSpotDataSource(): AdasFeatureDataSource {
        return BlindSpotMockDataSource()
    }

    @Provides
    @IntoMap
    @AdasFeatureKey(AdasFeatureType.FORWARD_COLLISION_WARNING)
    fun provideCollisionWarningDataSource(): AdasFeatureDataSource {
        return CollisionWarningMockDataSource()
    }

    @Provides
    @Singleton
    fun provideAdasDataSource(
        dataSources: Map<AdasFeatureType, @JvmSuppressWildcards AdasFeatureDataSource>
    ): AdasDataSource {
        return AdasDataSourceImpl(dataSources)
    }

    @Provides
    @Singleton
    fun provideAdasRepository(
        adasDataSource: AdasDataSource,
    ): AdasRepository {
        return AdasRepositoryImpl(
            Dispatchers.Default,
            adasDataSource
        )
    }
}