package com.example.chronomaster.task.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserCache)

    @Query("SELECT * FROM users")
    suspend fun users(): List<UserCache>

    @Query("DELETE FROM users where user_id = :userId")
    suspend fun delete(userId: Long)

    @Query("SELECT * FROM users where user_id = :userId")
    suspend fun user(userId: Long): UserCache
}
