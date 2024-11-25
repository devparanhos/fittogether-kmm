package br.com.fittogether.presentation.feature.signup.gender.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fittogether.presentation.feature.signup.gender.intent.GenderIntent
import br.com.fittogether.presentation.feature.signup.gender.state.GenderState
import br.com.fittogether.presentation.feature.signup.gender.viewmodel.GenderViewModel
import br.com.fittogether.presentation.ui.color.Secondary
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun GenderScreen(
    viewModel: GenderViewModel = koinViewModel(),
    navigateToStart: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    GenderContent(
        state = state,
        action = viewModel::submitIntent,
        navigateToStart = navigateToStart
    )
}

@Composable
fun GenderContent(
    state: GenderState,
    action: (GenderIntent) -> Unit,
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
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White)
                    .clickable {
                        action(GenderIntent.Logout)
                    }
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Column {
                        Text(
                            text = "Cadastro de conta",
                            fontSize = 18.sp
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Etapa 2 de 6: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        color = Secondary
                                    )
                                ) {
                                    append("gênero")
                                }
                            }
                        )
                    }
                }

                Text(
                    text = "Sair",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "GENDER"
            )
        }
    }
}