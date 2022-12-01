package com.niqr.worker.ui.screens.work.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.niqr.worker.R

@Composable
fun PreferencesDialog(
    maxValue: String,
    percentValue: String,
    onMaxChange: (String) -> Unit,
    onPercentChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = stringResource(R.string.save))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(R.string.default_fields),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NumberField(
                    value = maxValue,
                    onValueChange = onMaxChange,
                    label = { Text(stringResource(R.string.max)) }
                )
                NumberField(
                    value = percentValue,
                    onValueChange = onPercentChange,
                    label = { Text(stringResource(R.string.percent)) },
                    imeAction = ImeAction.Done,
                    max = 100
                )
            }
        }
    )
}