package br.com.fittogether.presentation.feature.welcome.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.fittogether.presentation.component.button.DefaultButton
import br.com.fittogether.presentation.ui.color.Primary
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.button_begin
import fittogether_app.composeapp.generated.resources.label_already_has_account
import fittogether_app.composeapp.generated.resources.label_enter_here
import fittogether_app.composeapp.generated.resources.subtitle_welcome
import fittogether_app.composeapp.generated.resources.title_welcome
import fittogether_app.composeapp.generated.resources.welcome_background

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeScreen(
    navigateOnboarding: () -> Unit,
    navigateToLogin: () -> Unit
) {
    WelcomeContent(
        navigateOnboarding = navigateOnboarding,
        navigateToLogin = navigateToLogin
    )
}

@Composable
fun WelcomeContent(
    navigateOnboarding: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.welcome_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .background(Primary.copy(alpha = 0.7f))
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(32.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = stringResource(Res.string.title_welcome),
                    color = Secondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(bottom = 24.dp),
                    text = stringResource(Res.string.subtitle_welcome),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
                DefaultButton(
                    label = stringResource(Res.string.button_begin),
                    onClick = {
                        navigateOnboarding()
                    }
                )
                Row(
                    modifier = Modifier.padding(top = 16.dp).clickable {
                        navigateToLogin()
                    }
                ) {
                    Text(
                        text = stringResource(Res.string.label_already_has_account),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = stringResource(Res.string.label_enter_here),
                        color = Secondary,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}