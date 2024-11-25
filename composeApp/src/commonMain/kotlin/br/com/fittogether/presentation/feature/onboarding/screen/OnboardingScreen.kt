package br.com.fittogether.presentation.feature.onboarding.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import br.com.fittogether.presentation.feature.onboarding.enums.page.OnboardingPages
import br.com.fittogether.presentation.ui.color.Complementary
import br.com.fittogether.presentation.ui.color.Primary
import br.com.fittogether.presentation.ui.color.Secondary

import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.ic_chevron_right

import kotlinx.coroutines.launch

import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingScreen(
    navigateToStart : () -> Unit
) {
    OnboardingContent(
        navigateToStart = navigateToStart
    )
}

@Composable
fun OnboardingContent(
    navigateToStart : () -> Unit
) {
    val pages = rememberPagerState(pageCount = { OnboardingPages.entries.size })
    var currentOnboarding by mutableStateOf(OnboardingPages.getOnboardingPage(0))
    val coroutine = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 56.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    repeat(pages.pageCount) { iteration ->
                        val color = if (pages.currentPage == iteration) Secondary else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
                FloatingActionButton(
                    modifier = Modifier.size(50.dp),
                    backgroundColor = Complementary,
                    content = {
                        Image(
                            painter = painterResource(Res.drawable.ic_chevron_right),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    onClick = {
                        if (pages.currentPage == pages.pageCount - 1) {
                            navigateToStart()
                        } else {
                            coroutine.launch {
                                currentOnboarding = OnboardingPages.getOnboardingPage(pages.currentPage + 1)
                                pages.scrollToPage(pages.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
    ) {
        HorizontalPager(
            state = pages,
            userScrollEnabled = false
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Box {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(currentOnboarding.background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    modifier = Modifier
                        .background(color = Primary.copy(alpha = 0.7f))
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f)
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentOnboarding.title,
                            color = Secondary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = currentOnboarding.subtitle,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}