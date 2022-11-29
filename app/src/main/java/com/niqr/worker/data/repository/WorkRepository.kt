package com.niqr.worker.data.repository

import com.niqr.worker.data.model.Work

interface WorkRepository {
    suspend fun insertWork(newWork: Work)
}