package com.example.chronomaster.task.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks"
)
data class TaskCache(
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val taskId: Long,
    @ColumnInfo(name = "task_title")
    val title: String,
    @ColumnInfo(name = "task_text")
    val content: String,
    @ColumnInfo(name = "task_price")
    val price: Int,
    @ColumnInfo(name = "start_time")
    val startTime: Long,
    @ColumnInfo(name = "finish_time")
    val finishTime: Long,
    @ColumnInfo(name = "category_id")
    val categoryId: Long,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean
)