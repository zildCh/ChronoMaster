package com.example.chronomaster.task.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chronomaster.task.list.TaskUi
import com.example.chronomaster.task.core.TasksRepository
import com.example.chronomaster.task.core.CategoryRepository
import com.example.chronomaster.task.list.CategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
@HiltViewModel
class TaskEditViewModel @Inject constructor(
    private val repository: TasksRepository.Edit,
    private val categoryRepository: CategoryRepository.All,
    @Named("IO")
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val taskLiveData: StateFlow<TaskUi>
        get() = _taskLiveData
    private val _taskLiveData: MutableStateFlow<TaskUi> = MutableStateFlow(TaskUi(-1L, "", "", 0, 0L, 0L, -1L, isCompleted = false))

/*
    val categoryLiveData: StateFlow<CategoryUi>
        get() = _categoryLiveData
    private val _categoryLiveData: MutableStateFlow<CategoryUi> = MutableStateFlow
*/

    val categoryLiveData: LiveData<List<CategoryUi>>
        get() = _categoryLiveData
    private val _categoryLiveData: MutableLiveData<List<CategoryUi>> = MutableLiveData()

    fun init(taskId: Long) {
        viewModelScope.launch(dispatcher) {
            val task = repository.task(taskId).toUi()
            _categoryLiveData.value = categoryRepository.categories().map { it.toUi() }
            _taskLiveData.emit(task)
        }
    }

    fun delete(taskId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.deleteTask(taskId)
        }
    }

    fun update(
        taskId: Long,
        newTitle: String,
        newContent: String,
        newPrice: Int,
        newStartTime: Long,
        newFinishTime: Long,
        categoryId: Long,
        currentCategory: Long,
        isCompleted: Boolean
    ) {
        viewModelScope.launch(dispatcher) {
            repository.updateTask(taskId, newTitle, newContent, newPrice, newStartTime, newFinishTime, categoryId, isCompleted)
        }
    }
}