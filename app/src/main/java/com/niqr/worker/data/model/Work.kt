package com.niqr.worker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Work(
    @PrimaryKey(/*autoGenerate = true*/) val id: Int,
    @ColumnInfo val sum: Double,
    @ColumnInfo val max: Double,
    @ColumnInfo val percent: Double,
    @ColumnInfo val totalWithdraw: Int,
    @ColumnInfo val keep: Double,
    @ColumnInfo val tasks: Tasks
)
