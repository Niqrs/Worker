package com.niqr.worker.data.model

data class Work(
    val sum: Double,
    val max: Double,
    val percent: Double,
    val totalWithdraw: Int,
    val keep: Double,
    val tasks: List<Pair<Boolean, Int>>
)
