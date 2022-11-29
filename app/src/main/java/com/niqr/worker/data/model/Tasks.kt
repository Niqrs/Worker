package com.niqr.worker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Tasks(
    val list: List<Pair<Boolean, Int>>
)