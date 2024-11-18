package br.com.fittogether.presentation.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource

@Composable
fun DefaultTopbar(
    navigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigate()
            }
            .padding(24.dp)
    ) {
        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = null
        )
    }
}