package br.com.fittogether.presentation.feature.signup.confirmCode.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

import br.com.fittogether.presentation.component.button.CountButton
import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.dialog.ErrorDialog
import br.com.fittogether.presentation.component.input.InputCode
import br.com.fittogether.presentation.component.topbar.DefaultTopbar
import br.com.fittogether.presentation.feature.signup.confirmCode.intent.ConfirmCodeIntent
import br.com.fittogether.presentation.feature.signup.confirmCode.state.ConfirmCodeState
import br.com.fittogether.presentation.feature.signup.confirmCode.viewmodel.ConfirmCodeViewModel
import br.com.fittogether.presentation.ui.color.Error
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Primary
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.confirm_code_subtitle
import fittogether_app.composeapp.generated.resources.confirm_code_title
import fittogether_app.composeapp.generated.resources.validate_code_code_not_recevied
import fittogether_app.composeapp.generated.resources.validate_code_code_sent
import fittogether_app.composeapp.generated.resources.validate_code_resend_code
import fittogether_app.composeapp.generated.resources.validate_code_verify_code

import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ConfirmCodeScreen(
    viewModel: ConfirmCodeViewModel = koinViewModel(),
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

    when {
        state.navigateToCreateUser -> {
            LaunchedEffect(true) {
                navigateToCreateUser(state.email)
            }
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
                            action(ConfirmCodeIntent.OpenCloseDialog)
                        }
                    )
                }
            )
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = White,
        topBar = {
            DefaultTopbar {
                navigateBack()
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 24.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.validate_code_code_not_recevied),
                    color = Grey400,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                CountButton(
                    modifier = Modifier.height(50.dp).padding(top = 8.dp),
                    isRequesting = state.isResendingCode,
                    enabled = state.canResendCode,
                    borderColor = Primary,
                    label = stringResource(Res.string.validate_code_resend_code),
                    backgroundColor = Primary,
                    secondsLeft = state.secondsLeft,
                    onClick = {
                        action(ConfirmCodeIntent.ResendCode)
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
                for (i in 0..5) {
                    InputCode(
                        modifier = Modifier.weight(1.0f).focusRequester(focusRequesters[i]),
                        number = state.listCodes[i],
                        imeAction = if (i == 5) ImeAction.Done else ImeAction.Next,
                        hasError = state.fieldErrors?.get("code") != null,
                        onTextChange = { text ->
                            if (text.isEmpty() && i > 0) {
                                focusRequesters[i - 1].requestFocus()
                            } else if (text.length == 1 && i <= 4) {
                                focusRequesters[i + 1].requestFocus()
                            } else if (i == 5) {
                                keyboard.clearFocus()
                            }

                            action(ConfirmCodeIntent.UpdateCode(code = text, index = i))
                        }
                    )
                }
            }
            state.fieldErrors?.get("code")?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = Error
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            DefaultButton(
                modifier = Modifier.height(46.dp),
                enabled = !state.isVerifyingCode,
                isRequesting = state.isVerifyingCode,
                label = stringResource(Res.string.validate_code_verify_code),
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
                text = stringResource(Res.string.validate_code_code_sent),
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
            }
        }
    }
}