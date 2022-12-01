package com.niqr.worker.ui.screens.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.worker.data.datastore.StoreWorkPreferences
import com.niqr.worker.data.model.WorkPreferences
import com.niqr.worker.data.repository.WorkRepository
import com.niqr.worker.ui.screens.work.model.WorkScreenUiState
import com.niqr.worker.ui.screens.work.model.toEntity
import com.niqr.worker.utils.numPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    val repository: WorkRepository,
    private val workPreferences: StoreWorkPreferences
) : ViewModel() {
    private val _workScreenUiState = MutableStateFlow(WorkScreenUiState())
    init { //Work preferences
        viewModelScope.launch() {
            workPreferences.getPreferences.take(1).onEach { preferences ->
                _workScreenUiState.value = WorkScreenUiState(
                    max = preferences.maxWithdraw,
                    percent = preferences.percent,
                    preferencesMax = preferences.maxWithdraw,
                    preferencesPercent = preferences.percent
                )
            }.collect()
        }
    }
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

    fun onPreferencesClick() {
        _workScreenUiState.update {
            it.copy(
                isPreferencesOpen = true
            )
        }
    }

    fun onPreferencesDismissRequest() {
        _workScreenUiState.update {
            it.copy(
                isPreferencesOpen = false
            )
        }
    }

    fun onPreferencesConfirmClick() {
        if (arePreferencesFieldsNotEmpty()) {
            updatePreferences()
            onPreferencesDismissRequest()
        }
    }

    private fun updatePreferences() {
        val state = _workScreenUiState.value
        viewModelScope.launch {
            workPreferences.setPreferences(
                WorkPreferences(
                    maxWithdraw = state.preferencesMax,
                    percent = state.preferencesPercent
                )
            )
        }
    }

    fun onPreferencesMaxChange(newValue: String) {
        onNumChange(newValue).let { str ->
            if (str != null) {
                _workScreenUiState.update {
                    it.copy(preferencesMax = str)
                }
            }
        }
    }

    fun onPreferencesPercentChange(newValue: String) {
        onNumChange(newValue).let { str ->
            if (str != null) {
                _workScreenUiState.update {
                    it.copy(preferencesPercent = str)
                }
            }
        }
    }

    fun areFieldsNotEmpty(): Boolean {
        val state = workScreenUiState.value
        return state.sum.isNotEmpty() &&
               state.max.isNotEmpty() &&
               state.percent.isNotEmpty()
    }

    private fun arePreferencesFieldsNotEmpty(): Boolean {
        val state = workScreenUiState.value
        return state.preferencesMax.isNotEmpty() &&
               state.preferencesPercent.isNotEmpty()
    }

    fun onFabClick() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWork(
                workScreenUiState.value.toEntity()
            )
        }
    }

    private fun onNumChange(newValue: String): String? {
        return if (numPattern.matches(newValue)) newValue else null
    }
}