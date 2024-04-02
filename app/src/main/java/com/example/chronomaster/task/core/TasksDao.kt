package com.example.chronomaster.task.core
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskCache)

    @Query("SELECT * FROM tasks where task_id = :taskId")
    suspend fun task(taskId: Long): TaskCache

    @Query("SELECT * FROM tasks")
    suspend fun tasks(): List<TaskCache>
    @Query("DELETE FROM tasks WHERE task_id = :taskId")
    suspend fun delete(taskId: Long)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()
}

/*
    @Query("SELECT * FROM tasks WHERE start_time = :startTime")
    suspend fun tasksWithStartTime(startTime: Long): List<TaskCache>
    @Query("SELECT * FROM tasks WHERE finish_time = :finishTime")
    suspend fun tasksWithFinishTime(finishTime: Long): List<TaskCache>
}*/
