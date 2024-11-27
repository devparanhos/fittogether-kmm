package br.com.fittogether.presentation.feature.registration.goal.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Secondary

@Composable
fun GoalScreen() {
    GoalContent()
}

@Composable
fun GoalContent() {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = Background,
        topBar = {
            Card {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White)
                        .clickable {

                        }
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Column {
                            Text(
                                text = "Completar cadastro",
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
                                            color = Secondary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append("Objetivos")
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

        }
    ) {

    }
}