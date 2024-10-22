package com.example.chronomaster.game.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chronomaster.task.core.UserCache

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: AnimalCache)

    @Query("SELECT * FROM animals")
    suspend fun animals(): List<AnimalCache>

    @Query("DELETE FROM animals where animal_id = :animalId")
    suspend fun delete(animalId: Long)

    @Query("SELECT * FROM animals where animal_id = :animalId")
    suspend fun animal(animalId: Long): AnimalCache
}
