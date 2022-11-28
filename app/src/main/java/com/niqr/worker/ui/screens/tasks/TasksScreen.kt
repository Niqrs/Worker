package com.niqr.worker.ui.screens.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niqr.worker.R
import com.niqr.worker.ui.screens.tasks.components.TasksBottomBar
import com.niqr.worker.ui.screens.tasks.components.TasksTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: TasksViewModel) {
    val state = viewModel.tasksScreenUiState.collectAsState()

    if (state.value != null) {
        Scaffold(
            topBar = {
                TasksTopBar(
                    num = "76800",
                    description = { Text(text = stringResource(R.string.total_withdraw)) }
                )
            },
            bottomBar = {
                TasksBottomBar(
                    num = "700",
                    description = { Text(stringResource(R.string.keep)) }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = stringResource(R.string.tasks), modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth())
                    Spacer(modifier = Modifier.height(4.dp))
                }
//                items(state.tasks) {
//                    TasksItem(
//                        task = TasksItemUiState(isDone = it.isDone, num = it.num),
//                        onDoneChange = {}
//                    )
//                }
                items(10) {
                    Text(text = state.toString())
                }
                item {
                    Spacer(modifier = Modifier.height(128.dp))
                }
            }
        }
    } else {
        Column {
            Text(text = "Null")
            Text(text = state.toString())

        }
    }
}