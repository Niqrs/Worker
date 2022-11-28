package com.niqr.worker.ui.screens.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.worker.data.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    repository: TasksRepository
) : ViewModel() {
    val tasksScreenUiState = repository.getWork().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )
//        TasksScreenUiState(
//            total = 86000,
//            keep = 1000,
//            tasks = listOf(
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12500),
//                TasksItemUiState(true, 12490),
//            )
//        )
//    val tasksScreenUiState = _tasksScreenUiState.asStateFlow()

}