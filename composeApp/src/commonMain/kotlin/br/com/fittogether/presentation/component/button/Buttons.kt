package br.com.fittogether.presentation.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.fittogether.presentation.ui.color.Complementary

import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DefaultButton(
    label: String,
    backgroundColor: Color = Complementary,
    borderColor: Color = Complementary,
    textColor: Color = Color.White,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth().height(46.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        onClick = {
            onClick()
        },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                icon?.let {
                    Image(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text(
                    text = label,
                    style = TextStyle(
                        fontSize = 16.sp,
                        letterSpacing = 1.sp
                    ),
                    fontWeight = FontWeight.Normal,
                    color = textColor,
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewButtons() {
    DefaultButton(
        label = "Botão",
        onClick = {}
    )
}