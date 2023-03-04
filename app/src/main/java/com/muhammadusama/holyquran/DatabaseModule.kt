package com.muhammadusama.holyquran

import android.content.Context
import androidx.room.Room
import com.muhammadusama.holyquran.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): QuranDatabase{
        return Room.databaseBuilder(context,QuranDatabase::class.java,Constants.DB_NAME).build()
    }
}