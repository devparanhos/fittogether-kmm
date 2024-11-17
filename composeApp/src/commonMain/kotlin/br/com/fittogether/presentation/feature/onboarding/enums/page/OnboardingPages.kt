package br.com.fittogether.presentation.feature.onboarding.enums.page


import fittogether_app.composeapp.generated.resources.Res
import fittogether_app.composeapp.generated.resources.onboarding_levelup_background
import fittogether_app.composeapp.generated.resources.onboarding_partner_background
import fittogether_app.composeapp.generated.resources.onboarding_together_background
import org.jetbrains.compose.resources.DrawableResource

enum class OnboardingPages(
    val position: Int,
    val title: String,
    val subtitle: String,
    val background: DrawableResource
) {
    PARTNER(
        position = 0,
        background = Res.drawable.onboarding_partner_background,
        title =  "Encontre um parceiro",
        subtitle = "Faça exercicios com um parceiro e obtenha melhores resultados porque treinar junto é melhor que sozinho."
    ),
    LEVELUP(
        position = 1,
        background = Res.drawable.onboarding_levelup_background,
        title =  "Aumente seu nível",
        subtitle = "Ganhe experiência e aumente seu nível e tenha acesso a benefícios exclusivos no app"

    ),
    TOGETHER(
        position = 2,
        background = Res.drawable.onboarding_together_background,
        title =  "Juntos somos mais",
        subtitle = "Agora, incentive seu parceiro a dar o melhor de si nos exercicios assim vocês vão alcançar sua melhor forma."

    );

    companion object {
        fun getOnboardingPage(position: Int): OnboardingPages {
            return entries.find { it.position == position } ?: PARTNER
        }
    }
}