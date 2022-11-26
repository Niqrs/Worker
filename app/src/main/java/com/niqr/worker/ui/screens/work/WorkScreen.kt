package com.niqr.worker.ui.screens.work

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WorkScreen(viewModel: WorkViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "work", modifier = Modifier.align(Alignment.Center))
    }
}