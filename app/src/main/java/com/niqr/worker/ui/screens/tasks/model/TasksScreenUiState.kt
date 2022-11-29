package com.niqr.worker.ui.screens.tasks.model

import com.niqr.worker.data.model.Tasks
import com.niqr.worker.data.model.Work

data class TasksScreenUiState(
    val id: Int,
    val sum: Double,
    val max: Double,
    val percent: Double,
    val totalWithdraw: Int,
    val keep: Double,
    val tasks: List<TasksItemUiState>
)

fun Work.toUiState(): TasksScreenUiState {
    val tasks = this.tasks.list.map {
        TasksItemUiState(it.first, it.second)
    }

    return TasksScreenUiState(
        id = this.id,
        sum = this.sum,
        max = this.max,
        percent = this.percent,
        totalWithdraw = this.totalWithdraw,
        keep = this.keep,
        tasks = tasks
    )
}

fun TasksScreenUiState.toEntity(): Work {
    val tasks = this.tasks.map {
        Pair(it.isDone, it.num)
    }

    return Work(
        id = this.id,
        sum = this.sum,
        max = this.max,
        percent = this.percent,
        totalWithdraw = this.totalWithdraw,
        keep = this.keep,
        tasks = Tasks(tasks)
    )
}