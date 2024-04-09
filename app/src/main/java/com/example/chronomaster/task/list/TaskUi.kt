package com.example.chronomaster.task.list

import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
data class TaskUi(
    private val taskId: Long,
    private val title: String,
    private val content: String,
    private val price: Int,
    private val startTime: Long,
    private val finishTime: Long,
    private val categoryId: Long,
    private val isCompleted: Boolean
) {
    fun isIdTheSame(taskId: Long) = taskId == this.taskId

    fun isIdTheSame(taskUi: TaskUi) = this.taskId == taskUi.taskId

    fun isCategoryIdTheSame(id: Long) = this.categoryId == id

    fun showTitle(textView: TextView) {
        textView.text = title
    }

    fun showCategory(spinner: Spinner) {
        val adapter = spinner.adapter
        var category: CategoryUi
        for (i in 0 until adapter.count) {
            category = adapter.getItem(i) as CategoryUi
            if (category.id() == categoryId) {
                spinner.setSelection(i)
            }
        }
    }

    fun showDueTime(textView: TextView): String {
        val date = Date(finishTime)
        val format = SimpleDateFormat("dd.MM.yyyy, HH:mm")
        textView.text = format.format(date)
        return format.format(date)
    }
    fun showIsCompleted(checkBox: CheckBox) {
        checkBox.isChecked = isCompleted
    }
    fun showContent(textView: TextView) {
        textView.text = content
    }
    fun showPrice(textView: TextView) {
        textView.text = price.toString()
    }
}
/*
    fun detailsScreen(categoryId: Long) : NoteDetailsScreen =
        NoteDetailsScreen(id, categoryId)
}*/
