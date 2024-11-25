package br.com.fittogether.presentation.feature.signup.createUser.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.fittogether.core.util.DateVisualTransformation

import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.component.dialog.ErrorDialog
import br.com.fittogether.presentation.component.input.DefaultInput
import br.com.fittogether.presentation.component.input.InputPhone
import br.com.fittogether.presentation.feature.signup.createUser.intent.CreateUserIntent
import br.com.fittogether.presentation.feature.signup.createUser.state.CreateUserState
import br.com.fittogether.presentation.feature.signup.createUser.viewmodel.CreateUserViewModel
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Grey500
import br.com.fittogether.presentation.ui.color.Grey600
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.ic_camera
import fittogether_app.composeapp.generated.resources.ic_eye
import fittogether_app.composeapp.generated.resources.ic_eye_off
import fittogether_app.composeapp.generated.resources.label_politics

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CreateUserScreen(
    viewModel: CreateUserViewModel = koinViewModel(),
    navigateToGender: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    CreateUserContent(
        state = state,
        action = viewModel::submitIntent,
        navigateToGender = navigateToGender
    )
}

@Composable
fun CreateUserContent(
    state: CreateUserState,
    navigateToGender: () -> Unit,
    action: (CreateUserIntent) -> Unit
) {
    val keyboard = LocalFocusManager.current
    var showPassword by remember { mutableStateOf(false) }

    when {
        state.openDialog -> {
            Dialog(
                onDismissRequest = {},
                properties = DialogProperties(dismissOnClickOutside = false),
                content = {
                    ErrorDialog(
                        internalCode = state.error?.internalCode,
                        message = state.error?.message,
                        onCancel = {
                            action(CreateUserIntent.OpenCloseDialog)
                        }
                    )
                }
            )
        }

        state.navigateToGender -> {
            navigateToGender()
        }
    }

    Scaffold(
        modifier = Modifier.statusBarsPadding().padding(WindowInsets.ime.asPaddingValues()),
        backgroundColor = Background,
        topBar = {
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
                                    append("Etapa 1 de 6: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
                                        color = Secondary
                                    )
                                ) {
                                    append("dados pessoais")
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
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Queremos saber mais sobre você",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            LazyColumn {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            shape = RoundedCornerShape(100.dp)
                        ) {
                            Row(
                                modifier = Modifier.size(110.dp).background(Grey500),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(36.dp),
                                    painter = painterResource(Res.drawable.ic_camera),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Grey600)
                                )
                            }
                        }
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = "Insira sua foto de perfil"
                        )
                    }
                    DefaultInput(
                        modifier = Modifier.padding(top = 24.dp),
                        text = state.name,
                        placeholder = "Nome completo",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            action(
                                CreateUserIntent.UpdateName(name = it)
                            )
                        },
                        messageError = state.fieldErrors?.get("name"),
                        hasError = state.fieldErrors?.get("name") != null
                    )
                    DefaultInput(
                        modifier = Modifier.padding(top = 16.dp),
                        text = state.email,
                        placeholder = "Email",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        readOnly = true,
                        trailingData = {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Secondary
                                )
                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    text = "validado",
                                    color = Secondary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        },
                        onValueChange = {

                        }
                    )
                    DefaultInput(
                        modifier = Modifier.padding(top = 16.dp),
                        text = state.username,
                        placeholder = "Usuario",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            action(
                                CreateUserIntent.UpdateUsername(username = it)
                            )
                        }
                    )
                    InputPhone(
                        modifier = Modifier.padding(top = 16.dp),
                        phone = state.cellphone,
                        label = "Celular",
                        prefix = "+55",
                        onTextChange = {
                            action(
                                CreateUserIntent.UpdateCellphone(cellphone = it)
                            )
                        },
                        messageError = state.fieldErrors?.get("cellphone"),
                        hasError = state.fieldErrors?.get("cellphone") != null
                    )
                    DefaultInput(
                        modifier = Modifier.padding(top = 16.dp),
                        text = state.birthdate,
                        placeholder = "Data de nascimento",
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                        visualTransformation = DateVisualTransformation(),
                        onValueChange = {
                            action(
                                CreateUserIntent.UpdateBirthdate(
                                    birthdate = it
                                )
                            )
                        },
                        messageError = state.fieldErrors?.get("birth_date"),
                        hasError = state.fieldErrors?.get("birth_date") != null
                    )
                    DefaultInput(
                        modifier = Modifier.padding(top = 16.dp),
                        isDataVisible = showPassword,
                        text = state.password,
                        placeholder = "Senha de acesso",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            action(
                                CreateUserIntent.UpdatePassword(password = it)
                            )
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
                    DefaultInput(
                        modifier = Modifier.padding(top = 16.dp),
                        text = state.confirmPassword,
                        isDataVisible = false,
                        placeholder = "Confirmação de senha",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        onValueChange = {
                            action(
                                CreateUserIntent.UpdateConfirmPassword(confirmPassword = it)
                            )
                        },

                        messageError = state.fieldErrors?.get("confirm_password"),
                        hasError = state.fieldErrors?.get("confirm_passowrd") != null
                    )
                    DefaultButton(
                        modifier = Modifier.padding(top = 24.dp).height(50.dp),
                        enabled = !state.isRequesting,
                        isRequesting = state.isRequesting,
                        label = "Salvar dados pessoais",
                        backgroundColor = Secondary,
                        borderColor = Secondary,
                        textColor = White,
                        onClick = {
                            keyboard.clearFocus()
                            action(CreateUserIntent.SaveUser)
                        }
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(Res.string.label_politics),
                        color = Grey400
                    )
                }
            }
        }
    }
}