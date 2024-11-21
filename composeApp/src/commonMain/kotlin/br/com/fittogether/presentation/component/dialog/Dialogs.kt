package br.com.fittogether.presentation.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.ui.color.Background
import br.com.fittogether.presentation.ui.color.Error
import br.com.fittogether.presentation.ui.color.Grey300
import br.com.fittogether.presentation.ui.color.Grey600
import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.ic_email_dialog
import fittogether_app.composeapp.generated.resources.ic_error_dialog
import org.jetbrains.compose.resources.painterResource

@Composable
fun ErrorDialog(
    internalCode: String? = null,
    message: String? = null,
    onCancel: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.9f),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Background)
                        .padding(24.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(Res.drawable.ic_error_dialog),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = "Algo deu errado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    internalCode?.let {
                        Text(
                            text = ": $internalCode",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                    text = "$message",
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DefaultButton(
                        modifier = Modifier.height(40.dp).weight(1.0f),
                        borderColor = Error,
                        backgroundColor = Error,
                        textColor = Color.White,
                        label = "Cancelar",
                        onClick = {
                            onCancel()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmailUsedDialog(
    onCancel: () -> Unit,
    toLogin:() -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.9f),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Background)
                        .padding(24.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(60.dp),
                        painter = painterResource(Res.drawable.ic_email_dialog),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "E-mail em uso",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                    text = "Já existe um usuário com o e-mail informado. Faça seu login",
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DefaultButton(
                        modifier = Modifier.height(40.dp).weight(1.0f),
                        borderColor = Grey300,
                        backgroundColor = Grey300,
                        textColor = Grey600,
                        label = "Cancelar",
                        onClick = {
                            onCancel()
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DefaultButton(
                        modifier = Modifier.height(40.dp).weight(1.0f),
                        borderColor = Error,
                        backgroundColor = Error,
                        label = "Login",
                        onClick = {
                            toLogin()
                        }
                    )
                }
            }
        }
    }
}