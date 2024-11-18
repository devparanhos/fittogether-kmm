package br.com.fittogether.presentation.feature.signup.verifyEmail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.input.DefaultInput
import br.com.fittogether.presentation.component.topbar.DefaultTopbar
import br.com.fittogether.presentation.feature.signup.verifyEmail.intent.VerifyEmailIntents
import br.com.fittogether.presentation.feature.signup.verifyEmail.state.VerifyEmailState
import br.com.fittogether.presentation.feature.signup.verifyEmail.viewmodel.VerifyEmailViewModel
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Grey600
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.ic_apple
import fittogether_app.composeapp.generated.resources.ic_google
import fittogether_app.composeapp.generated.resources.verify_email_input_email
import fittogether_app.composeapp.generated.resources.verify_email_register_label
import fittogether_app.composeapp.generated.resources.verify_email_register_with_apple
import fittogether_app.composeapp.generated.resources.verify_email_register_with_google
import fittogether_app.composeapp.generated.resources.verify_email_request_code_button
import fittogether_app.composeapp.generated.resources.verify_email_subtitle
import fittogether_app.composeapp.generated.resources.verify_email_title
import kotlinx.coroutines.launch

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun VerifyEmailScreen(
    viewModel: VerifyEmailViewModel = koinViewModel(),
    navigateToConfirmCode: (email: String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    VerifyEmailContent(
        state = state,
        action = viewModel::submitIntent,
        navigateBack = navigateBack,
        navigateToConfirmCode = navigateToConfirmCode
    )
}

@Composable
fun VerifyEmailContent(
    action: (VerifyEmailIntents) -> Unit,
    state: VerifyEmailState,
    navigateBack: () -> Unit,
    navigateToConfirmCode: (email: String) -> Unit
) {
    val keyboard = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    when {
        state.navigateToConfirmCode -> {
            LaunchedEffect(true) {
                navigateToConfirmCode(state.email)
            }
        }

        state.error != null -> {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = state.error.message ?: "Aconteceu algo de errado!",
                )
            }
        }
    }

    Scaffold(
        modifier = Modifier.background(Background).statusBarsPadding(),
        scaffoldState = scaffoldState,
        topBar = {
            DefaultTopbar {
                navigateBack()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(24.dp)
        ) {
            Text(
                text = stringResource(Res.string.verify_email_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = stringResource(Res.string.verify_email_subtitle),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultInput(
                text = state.email,
                placeholder = stringResource(Res.string.verify_email_input_email),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                onValueChange = {
                    action(VerifyEmailIntents.UpdateEmail(it))
                },
                onDone = {
                    keyboard.clearFocus()
                    action(VerifyEmailIntents.SendEmail)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                isRequesting = state.isRequesting,
                label = stringResource(Res.string.verify_email_request_code_button),
                backgroundColor = Secondary,
                borderColor = Secondary,
                textColor = White,
                onClick = {
                    keyboard.clearFocus()
                    action(VerifyEmailIntents.SendEmail)
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(
                    modifier = Modifier.height(1.dp).background(Grey400).weight(1f)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(Res.string.verify_email_register_label),
                    color = Grey400
                )
                Spacer(
                    modifier = Modifier.height(1.dp).background(Grey400).weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                label = stringResource(Res.string.verify_email_register_with_google),
                backgroundColor = White,
                borderColor = Grey600,
                textColor = Grey600,
                icon = painterResource(Res.drawable.ic_google),
                onClick = {

                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                label = stringResource(Res.string.verify_email_register_with_apple),
                backgroundColor = White,
                borderColor = Grey600,
                textColor = Grey600,
                icon = painterResource(Res.drawable.ic_apple),
                onClick = {

                }
            )
        }
    }
}