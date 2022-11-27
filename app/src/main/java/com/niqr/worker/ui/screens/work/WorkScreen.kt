package com.niqr.worker.ui.screens.work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.niqr.worker.R
import com.niqr.worker.ui.screens.work.components.NumberField
import com.niqr.worker.ui.screens.work.components.WorkFloatingActionButton
import com.niqr.worker.ui.screens.work.components.WorkTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkScreen(viewModel: WorkViewModel) {
    val state by viewModel.workScreenUiState.collectAsState()

    Scaffold(
        topBar = {
            WorkTopBar( {} ) // TODO
        },
        floatingActionButton = {
            WorkFloatingActionButton(
                onClick = viewModel::onFabClick
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