package com.niqr.worker.ui.screens.work.components

import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.niqr.worker.R

@Composable
fun WorkFloatingActionButton(
//    enabled: Boolean,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        text = { Text(stringResource(R.string.update)) },
        icon = {
            Icon(
                painterResource(R.drawable.round_sync_24),
                contentDescription = null,
            )
        }
    )
}