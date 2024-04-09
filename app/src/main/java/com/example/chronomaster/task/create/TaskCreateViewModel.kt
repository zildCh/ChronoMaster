package com.example.chronomaster.task.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chronomaster.task.core.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val repository: TasksRepository.Create,
    @Named("IO")
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    fun create(title: String, content: String, price: Int, startTime: Long, finishTime: Long, categoryId: Long) {
        viewModelScope.launch(dispatcher) {
            repository.create(title, content, price, startTime, finishTime, categoryId)
        }
    }
}