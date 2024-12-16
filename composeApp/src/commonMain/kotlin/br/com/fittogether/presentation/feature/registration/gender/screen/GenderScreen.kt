package br.com.fittogether.presentation.feature.registration.gender.screen

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
import br.com.fittogether.presentation.feature.registration.gender.intent.GenderIntent
import br.com.fittogether.presentation.feature.registration.gender.state.GenderState
import br.com.fittogether.presentation.feature.registration.gender.viewmodel.GenderViewModel
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Grey700
import br.com.fittogether.presentation.ui.color.Secondary

import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun GenderScreen(
    viewModel: GenderViewModel = koinViewModel(),
    navigateToStart: () -> Unit,
    navigateToGoals: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    GenderContent(
        state = state,
        action = viewModel::submitIntent,
        navigateToStart = navigateToStart,
        navigateToGoals = navigateToGoals
    )
}

@Composable
fun GenderContent(
    state: GenderState,
    action: (GenderIntent) -> Unit,
    navigateToStart: () -> Unit,
    navigateToGoals: () -> Unit
) {
    when {
        state.navigateToStart -> {
            LaunchedEffect(true) {
                navigateToStart()
            }
        }

        state.navigateToGoal -> {
            LaunchedEffect(true) {
                navigateToGoals()
            }
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = Background,
        topBar = {
            WizardTopbar(
                title = "Completar cadastro",
                step = "Gênero",
                rightLabel = "Sair",
                numberStep = "1",
                rightAction = {
                    action(GenderIntent.Logout)
                }
            )
        }
    ) {
        when {
            state.isLoading -> {
                LoadingContent()
            }

            state.genders != null -> {
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
                        items(state.genders) { gender ->
                            InputSelection(
                                selected = gender == state.selectedGender,
                                icon = gender.icon,
                                label = gender.name,
                                onSelection = {
                                    action(
                                        GenderIntent.SelectGender(
                                            gender = gender
                                        )
                                    )
                                }
                            )
                        }

                        item {
                            DefaultButton(
                                isRequesting = state.isRequesting,
                                enabled = !state.isRequesting,
                                label = "Salvar gênero",
                                backgroundColor = Secondary,
                                borderColor = Secondary,
                                onClick = {
                                    action(GenderIntent.SaveGender)
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