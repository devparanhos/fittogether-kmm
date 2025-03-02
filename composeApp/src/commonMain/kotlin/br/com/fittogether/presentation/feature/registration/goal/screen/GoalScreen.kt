package br.com.fittogether.presentation.feature.registration.goal.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.content.LoadingContent
import br.com.fittogether.presentation.component.input.InputSelection

import br.com.fittogether.presentation.component.topbar.WizardTopbar
import br.com.fittogether.presentation.feature.registration.goal.intent.GoalIntent
import br.com.fittogether.presentation.feature.registration.goal.state.GoalState
import br.com.fittogether.presentation.feature.registration.goal.viewmodel.GoalViewModel
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Grey700
import br.com.fittogether.presentation.ui.color.Secondary

import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun GoalScreen(
    viewModel: GoalViewModel = koinViewModel(),
    navigateToStart : () -> Unit
) {
    val state by viewModel.state.collectAsState()

    GoalContent(
        state = state,
        action = viewModel::submitIntent,
        navigateToStart = navigateToStart
    )
}

@Composable
fun GoalContent(
    state: GoalState,
    action: (GoalIntent) -> Unit,
    navigateToStart: () -> Unit
) {
    when {
        state.navigateToStart -> {
            LaunchedEffect(true) {
                navigateToStart()
            }
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = Background,
        topBar = {
            WizardTopbar(
                title = "Completar cadastro",
                step = "Objetivos",
                numberStep = "2",
                rightLabel = "Sair",
                rightAction = {
                    action(GoalIntent.Logout)
                }
            )
        }
    ) {
        when {
            state.isLoading -> {
                LoadingContent()
            }

            state.goals != null -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = "${state.title}",
                        fontSize = 16.sp,
                        color = Grey700,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.goals) { goal ->
                            InputSelection(
                                selected = goal == state.goalsSelected.find { it.id == goal.id },
                                icon = goal.icon,
                                label = goal.name,
                                onSelection = {
                                    action(
                                        GoalIntent.SelectGoal(
                                            goal = goal
                                        )
                                    )
                                }
                            )
                        }

                        item {
                            DefaultButton(
                                isRequesting = false,
                                enabled = true,
                                label = "Salvar objetivos",
                                backgroundColor = Secondary,
                                borderColor = Secondary,
                                onClick = {
                                    action(GoalIntent.SetGoals)
                                }
                            )
                        }
                    }
                }
            }

            else -> {

            }
        }

    }
}