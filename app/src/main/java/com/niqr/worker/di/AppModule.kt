package com.niqr.worker.di

import android.content.Context
import androidx.room.Room
import com.niqr.worker.data.db.WorkDatabase
import com.niqr.worker.data.repository.TasksRepository
import com.niqr.worker.data.repository.WorkRepository
import com.niqr.worker.data.repository.WorkerDatabaseRepository
import com.niqr.worker.data.repository.WorkerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideWorkDb(
        @ApplicationContext context: Context
    ): WorkDatabase = Room.databaseBuilder(
        context.applicationContext,
        WorkDatabase::class.java,
        "work_database"
    ).build()

    @Singleton
    @Provides
    fun provideRepository(
        database: WorkDatabase
    ): WorkerRepository {
        return WorkerDatabaseRepository(database.workDao())
    }

    @Provides fun provideWorkRepository(
        repo: WorkerRepository
    ): WorkRepository {
        return repo
    }

    @Provides fun provideTasksRepository(
        repo: WorkerRepository
    ): TasksRepository {
        return repo
    }
}