package br.com.fittogether.presentation.component.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import br.com.fittogether.presentation.ui.color.Grey400
import br.com.fittogether.presentation.ui.color.Grey600

@Composable
fun DefaultInput(
    text: String,
    placeholder: String,
    onValueChange: (text: String) -> Unit
) {


    BasicTextField(
        modifier = Modifier.fillMaxWidth().height(46.dp),
        value = text,
        decorationBox = {
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Grey600,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text.ifBlank {
                        placeholder
                    }
                )
            }
        },
        onValueChange = {
            onValueChange(it)
        }
    )
}