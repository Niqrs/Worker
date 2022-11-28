package com.niqr.worker.di

import com.niqr.worker.data.repository.InMemoryWorkerRepository
import com.niqr.worker.data.repository.TasksRepository
import com.niqr.worker.data.repository.WorkRepository
import com.niqr.worker.data.repository.WorkerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(): WorkerRepository {
        return InMemoryWorkerRepository()
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