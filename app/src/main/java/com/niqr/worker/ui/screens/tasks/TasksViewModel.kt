package com.niqr.worker.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.worker.data.model.Tasks
import com.niqr.worker.data.repository.TasksRepository
import com.niqr.worker.ui.screens.tasks.model.toEntity
import com.niqr.worker.ui.screens.tasks.model.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    val repository: TasksRepository
) : ViewModel() {
    val tasksScreenUiState = repository.getWork().map { it?.toUiState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    fun updateTask(index: Int, isDone: Boolean) {
//        val work = tasksScreenUiState.value!!.toEntity().
        val currentWork = tasksScreenUiState.value!!.toEntity()
        val newTasks = tasksScreenUiState.value!!.toEntity().tasks.list.toMutableList().let {
            it[index] = Pair(isDone, it[index].second)
            it
        }
        val newWork = currentWork.copy(
            tasks = Tasks(newTasks)
        )

        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWork(newWork)
        }
    }
}