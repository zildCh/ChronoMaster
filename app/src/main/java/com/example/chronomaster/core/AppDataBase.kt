package com.example.chronomaster.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chronomaster.game.core.AnimalCache
import com.example.chronomaster.game.core.AnimalDao
import com.example.chronomaster.task.core.TaskCache
import com.example.chronomaster.task.core.CategoryCache
import com.example.chronomaster.task.core.TasksDao
import com.example.chronomaster.task.core.CategoryDao
import com.example.chronomaster.task.core.UserCache
import com.example.chronomaster.task.core.UserDao

@Database(entities = [TaskCache::class, CategoryCache::class, UserCache::class, AnimalCache::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun notesDao(): TasksDao
    abstract fun userDao(): UserDao
    abstract fun animalDao(): AnimalDao
    abstract fun categoryDao(): CategoryDao
}