package br.com.fittogether.presentation.component.input

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.fittogether.core.util.keepOnlyNumbers
import br.com.fittogether.core.util.phoneFilter
import br.com.fittogether.presentation.ui.color.Error
import br.com.fittogether.presentation.ui.color.Grey300
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Grey700

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.brasil

import org.jetbrains.compose.resources.painterResource

@Composable
fun InputCode(
    modifier: Modifier = Modifier,
    number: String,
    imeAction: ImeAction,
    onTextChange: (text: String) -> Unit,
    hasError: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier.width(40.dp),
        shape = RoundedCornerShape(10.dp),
        value = number,
        onValueChange = { code ->
            if (code.all { it.isDigit() }) {
                if (code.length <= 1) {
                    onTextChange(code)
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = imeAction
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.White,
            unfocusedBorderColor = if (hasError) Error else Grey400,
            unfocusedLabelColor = if (hasError) Error else Grey400,
            focusedLabelColor = if (hasError) Error else Grey700,
            focusedBorderColor = if (hasError) Error else Grey700,
            cursorColor = Grey700
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )
    )
}

@Composable
fun DefaultInput(
    text: String,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onValueChange: (text: String) -> Unit,
    onDone: (() -> Unit)? = null,
    trailingData: @Composable (() -> Unit)? = null,
    hasError: Boolean = false,
    messageError: String? = null
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = Grey700,
            backgroundColor = Grey300
        )
    ) {
        Column {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                readOnly = readOnly,
                shape = RoundedCornerShape(10.dp),
                value = text,
                trailingIcon = {
                    trailingData?.invoke()
                },
                onValueChange = {
                    onValueChange(it)
                },
                label = {
                    Text(
                        text = placeholder
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = if (readOnly) { Grey300 } else { Color.White },
                    unfocusedBorderColor = if (hasError) Error else Grey400,
                    unfocusedLabelColor = if (hasError) Error else Grey400,
                    focusedLabelColor = if (hasError) Error else Grey700,
                    focusedBorderColor = if (hasError) Error else Grey700,
                    cursorColor = Grey700
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDone?.invoke()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                )
            )
            messageError?.let {
                Text(
                    text = it,
                    color = Error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun InputPhone(
    modifier: Modifier = Modifier,
    label: String,
    prefix: String,
    phone: String,
    hasError: Boolean = false,
    messageError: String? = null,
    onTextChange: (phone: String) -> Unit,
    onDone: (() -> Unit)? = null
) {
    val keyboard = LocalFocusManager.current

    Column {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            label = {
                Text(
                    text = label
                )
            },
            value = phone,
            onValueChange = {
                if (it.keepOnlyNumbers().length == 11) {
                    keyboard.clearFocus()
                }

                if (it.keepOnlyNumbers().length < 12) {
                    onTextChange(it)
                }
            },
            leadingIcon = {
                Row(
                    modifier = Modifier.padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(Res.drawable.brasil),
                        contentDescription = null
                    )
                    Text(
                        text = prefix
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White ,
                unfocusedBorderColor = if (hasError) Error else Grey400,
                unfocusedLabelColor = if (hasError) Error else Grey400,
                focusedLabelColor = if (hasError) Error else Grey700,
                focusedBorderColor = if (hasError) Error else Grey700,
                cursorColor = Grey700
            ),
            visualTransformation = { text -> phoneFilter(text) },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboard.clearFocus()
                    onDone?.invoke()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        messageError?.let {
            Text(
                text = it,
                color = Error,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
