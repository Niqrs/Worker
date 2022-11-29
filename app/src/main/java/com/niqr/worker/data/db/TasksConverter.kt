package com.niqr.worker.data.db

import androidx.room.TypeConverter
import com.niqr.worker.data.model.Tasks
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TasksConverter {

    @TypeConverter
    fun toTasks(tasks: String): Tasks {
        return Json.decodeFromString(string = tasks)
    }

    @TypeConverter
    fun toString(tasks: Tasks): String {
        return Json.encodeToString(tasks)
    }
}