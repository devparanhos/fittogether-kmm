package br.com.fittogether.presentation.feature.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.dialog.ErrorDialog
import br.com.fittogether.presentation.component.input.DefaultInput
import br.com.fittogether.presentation.component.topbar.DefaultTopbar
import br.com.fittogether.presentation.feature.login.intent.LoginIntent
import br.com.fittogether.presentation.feature.login.state.LoginState
import br.com.fittogether.presentation.feature.login.viewmodel.LoginViewModel
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Grey600
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.button_enter_with_apple
import fittogether_app.composeapp.generated.resources.button_enter_with_google
import fittogether_app.composeapp.generated.resources.ic_apple
import fittogether_app.composeapp.generated.resources.ic_eye
import fittogether_app.composeapp.generated.resources.ic_eye_off
import fittogether_app.composeapp.generated.resources.ic_google
import fittogether_app.composeapp.generated.resources.login_button_enter
import fittogether_app.composeapp.generated.resources.login_input_email
import fittogether_app.composeapp.generated.resources.login_input_password
import fittogether_app.composeapp.generated.resources.login_label_enter_with
import fittogether_app.composeapp.generated.resources.login_label_forgot_password
import fittogether_app.composeapp.generated.resources.login_subtitle
import fittogether_app.composeapp.generated.resources.login_title

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    navigateToGender: () -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LoginContent(
        state = state,
        action = viewModel::submitIntent,
        navigateToGender = navigateToGender,
        navigateBack = navigateBack
    )
}

@Composable
fun LoginContent(
    state: LoginState,
    action: (LoginIntent) -> Unit,
    navigateToGender: () -> Unit,
    navigateBack: () -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

    when {
        state.navigateToGender -> {
            navigateToGender()
        }

        state.openDialog -> {
            Dialog(
                onDismissRequest = {},
                properties = DialogProperties(dismissOnClickOutside = false),
                content = {
                    ErrorDialog(
                        internalCode = state.error?.internalCode,
                        message = state.error?.message,
                        onCancel = {
                            action(LoginIntent.OpenCloseDialog)
                        }
                    )
                }
            )
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding().padding(WindowInsets.ime.asPaddingValues()),
        backgroundColor = White,
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
                text = stringResource(Res.string.login_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = stringResource(Res.string.login_subtitle),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultInput(
                text = state.email,
                placeholder = stringResource(Res.string.login_input_email),
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                onValueChange = {
                    action(LoginIntent.UpdateEmail(email = it.lowercase().trim()))
                },
                messageError = state.fieldErrors?.get("email"),
                hasError = state.fieldErrors?.get("email") != null
            )
            DefaultInput(
                modifier = Modifier.padding(top = 8.dp),
                isDataVisible = showPassword,
                text = state.password,
                placeholder = stringResource(Res.string.login_input_password),
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onValueChange = {
                    action(LoginIntent.UpdatePassword(password = it))
                },
                trailingData = {
                    Row(
                        modifier = Modifier.clickable {
                            showPassword = !showPassword
                        }
                    ) {
                        Image(
                            painter = painterResource(if (showPassword) Res.drawable.ic_eye else Res.drawable.ic_eye_off),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp).padding(4.dp)
                        )
                    }
                },
                messageError = state.fieldErrors?.get("password"),
                hasError = state.fieldErrors?.get("password") != null
            )
            DefaultButton(
                modifier = Modifier.padding(top = 24.dp).height(50.dp),
                enabled = !state.isRequesting,
                isRequesting = state.isRequesting,
                label = stringResource(Res.string.login_button_enter),
                backgroundColor = Secondary,
                borderColor = Secondary,
                textColor = White,
                onClick = {
                    action(LoginIntent.Login)
                }
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(Res.string.login_label_forgot_password)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier.height(1.dp).fillMaxWidth().background(Grey400).weight(0.3f)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp).weight(0.5f).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(Res.string.login_label_enter_with),
                    color = Grey400
                )
                Spacer(
                    modifier = Modifier.height(1.dp).background(Grey400).weight(0.3f)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                label = stringResource(Res.string.button_enter_with_google),
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
                label = stringResource(Res.string.button_enter_with_apple),
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