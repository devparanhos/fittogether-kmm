package br.com.fittogether.presentation.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
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
import br.com.fittogether.presentation.feature.registration.gender.intent.GenderIntent
import br.com.fittogether.presentation.ui.color.Secondary
import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource

@Composable
fun DefaultTopbar(
    navigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigate()
            }
            .padding(24.dp)
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = null
        )
    }
}

@Composable
fun WizardTopbar(
    title: String,
    step: String,
    numberStep: String,
    rightLabel: String,
    rightAction: () -> Unit
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .clickable {
                    rightAction()
                }
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Column {
                    Text(
                        text = title,
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
                                append("Etapa $numberStep de 6: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    color = Secondary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(step)
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