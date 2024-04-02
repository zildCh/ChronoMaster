package com.example.chronomaster.task.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class UserCache(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Long,
    @ColumnInfo(name = "balance")
    val balance: Int
)