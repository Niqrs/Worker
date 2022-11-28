package com.niqr.worker.ui.screens.work.model

import com.niqr.worker.data.model.Work
import com.niqr.worker.utils.calculateTasks
import kotlin.math.roundToInt

data class WorkScreenUiState(
    val sum: String = "",
    val max: String = "",
    val percent: String = ""
)

fun WorkScreenUiState.toEntity(): Work {
    val sum = this.sum.toDouble()
    val max = this.max.toDouble()
    val percent = this.percent.toDouble()
    val totalWithdraw = ((1 - percent/100.0)*sum).roundToInt()
    val keep = sum-totalWithdraw
    val tasks = calculateTasks(totalWithdraw, max).map {
        Pair(false, it)
    }

    return Work(
        sum = sum,
        max = max,
        percent = percent,
        totalWithdraw = totalWithdraw,
        keep = keep,
        tasks = tasks
    )
}