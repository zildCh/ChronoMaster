package com.example.chronomaster.task.list

interface CategoryUi {
    fun isValid(task: TaskUi): Boolean

    fun id(): Long

    data class Base(
        private val id: Long,
        private val name: String
    ) : CategoryUi {
        override fun isValid(task: TaskUi): Boolean = task.isCategoryIdTheSame(id)
        override fun id(): Long = id

        override fun toString(): String {
            return name
        }
    }

    object Empty : CategoryUi {
        override fun isValid(task: TaskUi): Boolean = true
        override fun id(): Long = 0L

        override fun toString(): String {
            return ""
        }
    }
}