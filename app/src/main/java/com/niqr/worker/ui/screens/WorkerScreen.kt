package com.niqr.worker.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niqr.worker.navigation.NavigationTree
import com.niqr.worker.ui.screens.tasks.TasksScreen
import com.niqr.worker.ui.screens.tasks.TasksViewModel
import com.niqr.worker.ui.screens.work.WorkScreen
import com.niqr.worker.ui.screens.work.WorkViewModel

@Composable
fun WorkerScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Work.name) {
        composable(NavigationTree.Work.name) {
            val viewModel: WorkViewModel = hiltViewModel()
            WorkScreen(viewModel)
        }
        composable(NavigationTree.Tasks.name) {
            val viewModel: TasksViewModel = hiltViewModel()
            TasksScreen(viewModel)
        }
    }
}