package io.github.dox4.candybox.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.dox4.candybox.data.entity.BoxDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): BoxDatabase {
        return Room.databaseBuilder(
            app,
            BoxDatabase::class.java,
            app.applicationContext.packageName
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookOrWorldDao(db: BoxDatabase) = db.bookOrWorldDao()
}