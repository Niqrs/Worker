package com.niqr.worker.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.niqr.worker.navigation.NavigationTree
import com.niqr.worker.ui.components.WorkerNavigationBar
import com.niqr.worker.ui.screens.tasks.TasksScreen
import com.niqr.worker.ui.screens.tasks.TasksViewModel
import com.niqr.worker.ui.screens.work.WorkScreen
import com.niqr.worker.ui.screens.work.WorkViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun WorkerScreen() {
    val navController = rememberAnimatedNavController()

    val destinations = listOf(
        Pair(NavigationTree.Work.name, Icons.Rounded.Add),
        Pair(NavigationTree.Tasks.name, Icons.Rounded.Star)
    )

    Scaffold(
        bottomBar = {
            WorkerNavigationBar(navController, destinations)
        }
    ) { innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = NavigationTree.Work.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                NavigationTree.Work.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = LinearEasing // interpolator
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = LinearEasing
                        )
                    )
                }
            ) {
                val viewModel: WorkViewModel = hiltViewModel()
                WorkScreen(viewModel)
            }
            composable(
                NavigationTree.Tasks.name,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = LinearEasing // interpolator
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = LinearEasing
                        )
                    )
                }
            ) {
                val viewModel: TasksViewModel = hiltViewModel()
                TasksScreen(viewModel)
            }
        }
    }
}