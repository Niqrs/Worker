package com.niqr.worker.ui.screens.work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.niqr.worker.R
import com.niqr.worker.ui.screens.work.components.NumberField
import com.niqr.worker.ui.screens.work.components.WorkFloatingActionButton
import com.niqr.worker.ui.screens.work.components.WorkTopBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkScreen(
    viewModel: WorkViewModel,
//    navigateToTasks: () -> Unit
) {
    val state by viewModel.workScreenUiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            WorkTopBar {
                scope.launch(Dispatchers.IO) {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(
                        message = "There is no settings yet.",
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        },
        floatingActionButton = {
            WorkFloatingActionButton(
                onClick = {
                    if (viewModel.areFieldsNotEmpty()) {
                        viewModel.onFabClick()
//                        navigateToTasks()
                    }
                    else
                        scope.launch(Dispatchers.IO) {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                message = "Fields can't be empty!",
                                withDismissAction = true
                            )
                        }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NumberField(
                value = state.sum,
                onValueChange = viewModel::onSumChange,
                label = { Text(stringResource(R.string.sum)) }
            )

            NumberField(
                value = state.max,
                onValueChange = viewModel::onMaxChange,
                label = { Text(stringResource(R.string.max)) }
            )

            NumberField(
                value = state.percent,
                onValueChange = viewModel::onPercentChange,
                label = { Text(stringResource(R.string.percent)) },
                imeAction = ImeAction.Done,
                max = 100
            )
        }
    }
}