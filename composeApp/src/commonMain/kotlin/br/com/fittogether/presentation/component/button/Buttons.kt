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
import androidx.compose.material.CircularProgressIndicator
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

@Composable
fun DefaultButton(
    isRequesting: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color = Complementary,
    borderColor: Color = Complementary,
    textColor: Color = Color.White,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        modifier = modifier.fillMaxWidth().height(58.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isRequesting || !enabled) Color.Gray else borderColor
        ),
        onClick = {
            onClick()
        },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (isRequesting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.Gray
                    )
                } else {
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
        }
    )
}

@Composable
fun CountButton(
    secondsLeft: Int,
    isRequesting: Boolean = false,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color = Complementary,
    borderColor: Color = Complementary,
    textColor: Color = Color.White,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        modifier = modifier.fillMaxWidth().height(58.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isRequesting || !enabled) Color.Gray else borderColor
        ),
        onClick = {
            onClick()
        },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (isRequesting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.Gray
                    )
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
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
                                color = if(enabled) textColor else Color.Gray,
                            )
                        }
                        if (secondsLeft != 0) {
                            Text(
                                text = secondsLeft.toString()
                            )
                        }
                    }
                }
            }
        }
    )
}
