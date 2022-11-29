package com.niqr.worker.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.niqr.worker.data.model.Work
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {
    @Query("SELECT * FROM work ORDER BY id DESC LIMIT 1")
    fun getWork(): Flow<Work?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWork(work: Work)

    @Update
    fun updateWork(work: Work)
}