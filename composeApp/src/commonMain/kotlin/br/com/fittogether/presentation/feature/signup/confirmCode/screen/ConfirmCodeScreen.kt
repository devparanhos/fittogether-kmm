package br.com.fittogether.presentation.feature.signup.confirmCode.screen

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.fittogether.presentation.component.button.CountButton
import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.input.InputCode
import br.com.fittogether.presentation.feature.signup.confirmCode.intent.ConfirmCodeIntent
import br.com.fittogether.presentation.feature.signup.confirmCode.state.ConfirmCodeState
import br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel.ConfirmCodeViewModel
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Primary
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.confirm_code_subtitle
import fittogether_app.composeapp.generated.resources.confirm_code_title
import fittogether_app.composeapp.generated.resources.ic_arrow_back

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ConfirmCodeScreen(
    email: String,
    viewModel: ConfirmCodeViewModel = koinViewModel(parameters = { parametersOf(email) }),
    navigateBack: () -> Unit,
    navigateToCreateUser: (email: String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    ConfirmCodeContent(
        state = state,
        navigateBack = navigateBack,
        navigateToCreateUser = navigateToCreateUser,
        action = viewModel::submitIntent
    )
}

@Composable
fun ConfirmCodeContent(
    state: ConfirmCodeState,
    action: (ConfirmCodeIntent) -> Unit,
    navigateBack: () -> Unit,
    navigateToCreateUser: (email: String) -> Unit
) {
    val keyboard = LocalFocusManager.current
    val focusRequesters = remember {
        List(6) { FocusRequester() }
    }

    if (state.navigateToCreateUser) {
        LaunchedEffect(true) {
            navigateToCreateUser(state.email)
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = White,
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
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Não recebeu o código? Solicite o reenvio após o tempo",
                    color = Grey400,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                CountButton(
                    modifier = Modifier.height(50.dp).padding(top = 8.dp),
                    enabled = state.canResendCode,
                    borderColor = Primary,
                    label = "Reenviar o código",
                    backgroundColor = Primary,
                    secondsLeft = state.secondsLeft,
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
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                for (i in 0..5){
                    InputCode(
                        modifier = Modifier.weight(1.0f).focusRequester(focusRequesters[i]),
                        number = state.listCodes[i],
                        imeAction = if(i == 5) ImeAction.Done else ImeAction.Next,
                        onTextChange = {
                            if (it.length == 1 && i <= 4) {
                                focusRequesters[i + 1].requestFocus()
                            } else if (i == 5) {
                                keyboard.clearFocus()
                            }

                            action(ConfirmCodeIntent.UpdateCode(code = it, index = i))
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                isRequesting = false,
                label = "Verificar código",
                backgroundColor = Secondary,
                borderColor = Secondary,
                textColor = White,
                onClick = {
                    keyboard.clearFocus()
                    action(ConfirmCodeIntent.ValidateCode)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Código enviado para o e-mail",
                fontSize = 13.sp,
                color = Grey400
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = state.email,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "alterar",
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}