package com.niqr.worker.ui.screens.tasks

import androidx.lifecycle.ViewModel
import com.niqr.worker.ui.screens.tasks.model.TasksItemUiState
import com.niqr.worker.ui.screens.tasks.model.TasksScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {
    private val _tasksScreenUiState = MutableStateFlow(TasksScreenUiState(
        listOf(
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12500),
            TasksItemUiState(true, 12490),
        )
    ))
    val tasksScreenUiState = _tasksScreenUiState.asStateFlow()

}