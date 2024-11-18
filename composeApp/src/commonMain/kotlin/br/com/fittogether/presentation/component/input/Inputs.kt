package br.com.fittogether.presentation.component.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputCode(
    modifier: Modifier = Modifier,
    number: String,
    imeAction: ImeAction,
    onTextChange: (text: String) -> Unit
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
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Gray,
            cursorColor = Color.Gray
        )
    )
}

@Composable
fun DefaultInput(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onValueChange: (text: String) -> Unit,
    onDone: (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = placeholder
            )
        },
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
}
