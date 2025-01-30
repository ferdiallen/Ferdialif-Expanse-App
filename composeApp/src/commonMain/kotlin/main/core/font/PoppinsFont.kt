package main.core.font

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ferdialifcountermoney.composeapp.generated.resources.Res
import ferdialifcountermoney.composeapp.generated.resources.bold
import ferdialifcountermoney.composeapp.generated.resources.light
import ferdialifcountermoney.composeapp.generated.resources.regular
import ferdialifcountermoney.composeapp.generated.resources.semibold


@Composable
fun poppinsFont():FontFamily {
    val poppinsFont = FontFamily(
        listOf(
            org.jetbrains.compose.resources.Font(Res.font.regular),
            org.jetbrains.compose.resources.Font(Res.font.light, weight = FontWeight.Light),
            org.jetbrains.compose.resources.Font(Res.font.bold, weight = FontWeight.Bold),
            org.jetbrains.compose.resources.Font(Res.font.semibold, weight = FontWeight.SemiBold)
        )
    )
    return poppinsFont
}