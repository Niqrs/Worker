package com.niqr.worker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.niqr.worker.data.model.Work

@Database(entities = [Work::class], version = 1, exportSchema = false)
@TypeConverters(TasksConverter::class)
abstract class WorkDatabase() : RoomDatabase() {
    abstract  fun workDao(): WorkDao
}