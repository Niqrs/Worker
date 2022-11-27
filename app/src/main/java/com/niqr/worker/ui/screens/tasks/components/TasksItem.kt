package com.niqr.worker.ui.screens.tasks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.niqr.worker.ui.screens.tasks.model.TasksItemUiState

@Composable
fun TasksItem(
    modifier: Modifier = Modifier,
    task: TasksItemUiState,
    onDoneChange: (Boolean) -> Unit,
) {
    Column(
    ) {
        Divider(Modifier.height(1.dp))
        Surface(
            color = MaterialTheme.colorScheme.surfaceColorAtElevation(0.1.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 14.dp)
                    .height(54.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = onDoneChange
                    )
                    Text(text = task.num.toString())
                }
                Text(text = "withdraw", modifier.alpha(0.5f))
            }
        }
        Divider(Modifier.height(1.dp))
        Spacer(modifier = Modifier.height(5.dp))
    }
}