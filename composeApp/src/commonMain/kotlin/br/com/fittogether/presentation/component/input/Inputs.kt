package br.com.fittogether.presentation.component.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

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
