package com.mek.internshipproject.di

import android.content.Context
import androidx.room.Room
import com.mek.internshipproject.room.AppDatabase
import com.mek.internshipproject.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,AppDatabase::class.java,DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideLocationDao(database : AppDatabase) = database.locationDao()
}