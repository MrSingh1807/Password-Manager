package com.example.passwordmanagerassignment.modules

import android.content.Context
import androidx.room.Room
import com.example.passwordmanagerassignment.localDbLayer.LocalDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, LocalDb::class.java, "Password_Manager"
    ).build()


    @Singleton
    @Provides
    fun provideDao(db: LocalDb) = db.accessUserInfo()

}