package com.niqr.worker.data.repository

import com.niqr.worker.data.db.WorkDao
import com.niqr.worker.data.model.Work
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkerDatabaseRepository @Inject constructor(
    private val workDao: WorkDao
): WorkerRepository {
    override suspend fun insertWork(newWork: Work) {
        workDao.insertWork(newWork)
    }

    override fun getWork(): Flow<Work?> {
        return workDao.getWork()
    }

    override suspend fun updateWork(work: Work) {
        workDao.updateWork(work)
    }
}