import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import main.core.font.poppinsFont
import main.ui.main.MainCalculationScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.kodein.emoji.compose.EmojiService


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val poppinsFont = poppinsFont()
    MaterialTheme(
        typography = Typography(
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont),
            TextStyle(fontFamily = poppinsFont)
        )
    ) {
        MainCalculationScreen(modifier)
    }
}
