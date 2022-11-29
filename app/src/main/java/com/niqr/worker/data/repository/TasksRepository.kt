package com.niqr.worker.data.repository

import com.niqr.worker.data.model.Work
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getWork(): Flow<Work>
    suspend fun updateTask(index: Int, isDone: Boolean)
}