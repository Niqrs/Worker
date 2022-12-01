package com.niqr.worker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkPreferences(
    val maxWithdraw: String = "10000",
    val percent: String = "1.5"
)