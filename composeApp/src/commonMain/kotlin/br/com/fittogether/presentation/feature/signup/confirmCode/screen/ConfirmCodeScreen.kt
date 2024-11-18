package br.com.fittogether.presentation.feature.signup.confirmCode.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.input.InputCode
import br.com.fittogether.presentation.feature.signup.verifyEmail.intent.VerifyEmailIntents
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Primary
import br.com.fittogether.presentation.ui.color.Secondary
import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.confirm_code_subtitle
import fittogether_app.composeapp.generated.resources.confirm_code_title
import fittogether_app.composeapp.generated.resources.ic_arrow_back
import fittogether_app.composeapp.generated.resources.verify_email_request_code_button
import fittogether_app.composeapp.generated.resources.verify_email_subtitle
import fittogether_app.composeapp.generated.resources.verify_email_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ConfirmCodeScreen(
    email: String?,
    navigateBack: () -> Unit
) {
    ConfirmCodeContent(
        navigateBack = navigateBack
    )
}

@Composable
fun ConfirmCodeContent(
    navigateBack: () -> Unit
) {
    val focusRequesters = remember {
        List(6) { FocusRequester() }
    }

    Scaffold(
        modifier = Modifier.background(Background).statusBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigateBack()
                    }
                    .padding(24.dp)
            ) {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        },
        bottomBar = {
            Column {
                Text("Não recebeu o código? Solicite o reenvio após o tempo")
                DefaultButton(
                    modifier = Modifier.height(46.dp),
                    label = "teste",
                    backgroundColor = Primary,
                    onClick = {

                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(24.dp)
        ) {
            Text(
                text = stringResource(Res.string.confirm_code_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = stringResource(Res.string.confirm_code_subtitle),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                for (i in 0..5){
                    InputCode(
                        modifier = Modifier.weight(1.0f).focusRequester(focusRequesters[i]),
                        number = "0",
                        imeAction = if(i == 5) ImeAction.Done else ImeAction.Next,
                        onTextChange = {
                            if (it.length == 1 && i <= 4) {
                                focusRequesters[i + 1].requestFocus()
                            } else if (i == 5) {
//                                keyboard.clearFocus()
                            }

//                            viewModel.submitAction(
//                                action = ConfirmCodeAction.UpdateCode(
//                                    code = it,
//                                    index = i
//                                )
//                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                isRequesting = false,
                label = stringResource(Res.string.verify_email_request_code_button),
                backgroundColor = Secondary,
                borderColor = Secondary,
                textColor = White,
                onClick = {
//                    keyboard.clearFocus()
//                    action(VerifyEmailIntents.SendEmail)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Código enviado para o e-mail")
            Row {
                Text(text = "dev.paranhos@gmail.com")
                Text(text = "alterar")
            }
        }
    }
}