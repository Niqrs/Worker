package com.niqr.worker.ui.screens.tasks

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niqr.worker.R
import com.niqr.worker.ui.screens.tasks.components.TasksBottomBar
import com.niqr.worker.ui.screens.tasks.components.TasksItem
import com.niqr.worker.ui.screens.tasks.components.TasksTopBar
import com.niqr.worker.ui.screens.tasks.model.TasksItemUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: TasksViewModel) {
    val state by viewModel.tasksScreenUiState.collectAsState()

    Scaffold(
        topBar = {
            TasksTopBar(
                num = state?.totalWithdraw?.toString() ?: "0",
                description = { Text(text = stringResource(R.string.total_withdraw)) }
            )
        },
        bottomBar = {
            TasksBottomBar(
                num = state?.keep?.toString() ?: "0",
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
            if (state == null) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "There is no tasks",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .alpha(0.5f)
                    )
                }
            } else {
                itemsIndexed(state!!.tasks) { index, it ->
                    TasksItem(
                        task = TasksItemUiState(isDone = it.isDone, num = it.num),
                        onDoneChange = { isDone -> viewModel.updateTask(index, isDone) }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(128.dp))
            }
        }
    }
}