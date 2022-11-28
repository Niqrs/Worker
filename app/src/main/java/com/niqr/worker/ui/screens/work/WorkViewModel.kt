package com.niqr.worker.ui.screens.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.worker.data.repository.WorkRepository
import com.niqr.worker.ui.screens.work.model.WorkScreenUiState
import com.niqr.worker.ui.screens.work.model.toEntity
import com.niqr.worker.utils.numPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    val repository: WorkRepository
) : ViewModel() {
    private val _workScreenUiState = MutableStateFlow(WorkScreenUiState())
    val workScreenUiState = _workScreenUiState.asStateFlow()

    fun onSumChange(newValue: String) {
        onNumChange(newValue).let { str ->
            if (str != null)
                _workScreenUiState.update {
                    it.copy(sum = str)
                }
        }
    }

    fun onMaxChange(newValue: String) {
        onNumChange(newValue).let { str ->
            if (str != null)
                _workScreenUiState.update {
                    it.copy(max = str)
                }
        }
    }

    fun onPercentChange(newValue: String) {
        onNumChange(newValue).let { str ->
            if (str != null) {
                _workScreenUiState.update {
                    it.copy(percent = str)
                }
            }
        }
    }

    fun onFabClick() {
        viewModelScope.launch {
            repository.updateWork(
                workScreenUiState.value.toEntity()
            )
        }
    }

    private fun onNumChange(newValue: String): String? {
        return if (numPattern.matches(newValue)) newValue else null
    }
}