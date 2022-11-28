package com.niqr.worker.utils

import kotlin.math.ceil

fun calculateTasks(total: Int, max: Double): List<Int> {
    val tasksCount: Int = ceil(total.toDouble() / max).toInt()

    val firstPartItem = ceil(total.toDouble() / tasksCount).toInt()
    val firstPart = List(tasksCount - 1) { firstPartItem }
    val firstPartTotal = firstPartItem * (tasksCount - 1)

    val lastItem = total - firstPartTotal

    return firstPart + lastItem
}