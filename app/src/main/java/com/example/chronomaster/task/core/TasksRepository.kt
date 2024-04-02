package com.example.chronomaster.task.core

import com.example.chronomaster.core.Now
import javax.inject.Inject

interface NotesRepository {
    interface ReadList {
        //suspend fun tasks(startTime: Long): List<Task>
        suspend fun tasks(): List<Task>
    }

    interface Create {
        suspend fun create(title: String = "", content: String = "", price: Int,  startTime: Long, finishTime: Long, categoryId: Long)
    }

    interface Edit {
        suspend fun deleteTask(id: Long)
        suspend fun updateTask(id: Long, title: String = "", content: String = "", price: Int,  startTime: Long, finishTime: Long, categoryId: Long, isCompleted: Boolean)
        suspend fun task(id: Long): Task
    }

    interface All : ReadList, Create, Edit
    class Base @Inject constructor(
        private val now: Now,
        private val dao: TasksDao
    ) : All {
       /* override suspend fun tasks(startTime: Long): List<Task> =
            dao.tasksWithStartTime(startTime).map { Task(it.taskId, it.title, it.content, it.price, it.startTime, it.finishTime, it.categoryId, it.isCompleted) }*/
        override suspend fun tasks(): List<Task> =
            dao.tasks().map { Task(it.taskId, it.title, it.content, it.price, it.startTime, it.finishTime, it.categoryId, it.isCompleted) }

        override suspend fun create(title: String, content: String, price: Int,  startTime: Long, finishTime: Long, categoryId: Long) {
            dao.insert(TaskCache(now.timeInMillis(), title, content, price, startTime, finishTime, categoryId, false))
        }

        override suspend fun deleteTask(id: Long) {
            dao.delete(id)
        }

        override suspend fun updateTask(id: Long, title: String, content: String, price: Int,  startTime: Long, finishTime: Long, categoryId: Long, isCompleted: Boolean) {
            val task = dao.task(id)
            dao.insert(task.copy(title = title, content = content))
        }

        override suspend fun task(id: Long): Task =
            dao.task(id).let { Task(it.taskId, it.title, it.content, it.price, it.startTime, it.finishTime, it.categoryId, it.isCompleted) }

    }

}
data class Task(
    private val taskId: Long,
    private val title: String,
    private val content: String,
    private val price: Int,
    private val startTime: Long,
    private val finishTime: Long,
    private val categoryId: Long,
    private val isCompleted: Boolean
) {
 //fun toUi() =
 //NoteUi(id, title, content, date)
    fun categoryId() = categoryId
}