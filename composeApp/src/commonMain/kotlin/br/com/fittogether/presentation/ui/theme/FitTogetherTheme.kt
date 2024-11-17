package br.com.fittogether.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import br.com.fittogether.presentation.ui.color.Primary
import fittogether_app.composeapp.generated.resources.NunitoSans_10pt_Bold
import fittogether_app.composeapp.generated.resources.NunitoSans_10pt_Light
import fittogether_app.composeapp.generated.resources.NunitoSans_10pt_Medium
import fittogether_app.composeapp.generated.resources.NunitoSans_10pt_Regular
import fittogether_app.composeapp.generated.resources.NunitoSans_10pt_SemiBold
import fittogether_app.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun FitTogetherTheme(
    content : @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Primary,
            background = Color.White
        ),
        typography = Typography(
            defaultFontFamily = FontFamily(
                Font(resource = Res.font.NunitoSans_10pt_Regular, FontWeight.Normal),
                Font(resource = Res.font.NunitoSans_10pt_Light, FontWeight.Light),
                Font(resource = Res.font.NunitoSans_10pt_Medium, FontWeight.Medium),
                Font(resource = Res.font.NunitoSans_10pt_SemiBold, FontWeight.SemiBold),
                Font(resource = Res.font.NunitoSans_10pt_Bold, FontWeight.Bold)
            )
        )
    ){
        content()
    }
}