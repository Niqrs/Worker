package com.niqr.worker.data.repository

import android.util.Log
import com.niqr.worker.data.model.Work
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class InMemoryWorkerRepository @Inject constructor(): WorkerRepository {
    private val workChannel = Channel<Work>()

    override suspend fun updateWork(newWork: Work) {
        Log.d("Work", newWork.toString())
        workChannel.send(newWork)
    }

    override fun getWork(): Flow<Work> {
        return workChannel.receiveAsFlow()
    }
}