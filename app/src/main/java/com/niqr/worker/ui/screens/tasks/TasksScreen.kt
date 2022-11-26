package com.niqr.worker.ui.screens.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TasksScreen(viewModel: TasksViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "tasks", modifier = Modifier.align(Alignment.Center))
    }
}