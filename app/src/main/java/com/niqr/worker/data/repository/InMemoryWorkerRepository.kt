package com.niqr.worker.data.repository

import com.niqr.worker.data.model.Work
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class InMemoryWorkerRepository @Inject constructor(): WorkerRepository {
    private val workChannel = Channel<Work>()
    private var currentWork: Work? = null

    override suspend fun updateWork(newWork: Work) {
        currentWork = newWork
        workChannel.send(currentWork!!)
    }

    override fun getWork(): Flow<Work> {
        return workChannel.receiveAsFlow()
    }

    override suspend fun updateTask(index: Int, isDone: Boolean) {
        val newTasks = currentWork!!.tasks.toMutableList().let {
            it[index] = Pair(isDone, it[index].second)
            it
        }

        currentWork = currentWork!!.copy(
            tasks = newTasks
        )
        workChannel.send(currentWork!!)
    }
}