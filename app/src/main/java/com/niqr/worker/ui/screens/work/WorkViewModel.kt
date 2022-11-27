package com.niqr.worker.ui.screens.work

import androidx.lifecycle.ViewModel
import com.niqr.worker.ui.screens.work.model.WorkScreenUiState
import com.niqr.worker.utils.numPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor() : ViewModel() {
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

    }

    private fun onNumChange(newValue: String): String? {
        return if (numPattern.matches(newValue)) newValue else null
    }
}