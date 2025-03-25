import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import main.core.font.poppinsFont
import main.core.ui.NumberTextField
import main.core.ui.ToastPopup
import main.core.ui.ToastPopupData
import main.core.ui.rememberMoneyPopup
import main.ui.GreenTealColor
import main.ui.main.MainCalculationScreen


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

        /*MainCalculationScreen(modifier)*/
        /*Dialog(onDismissRequest = {}) {
            DialogLimitScreen(modifier, onSaved = {

            })
        }*/
    }
}

@Composable
fun Modifier.scrollbarTest(modifier: Modifier = Modifier) {

}

@Composable
fun DialogLimitScreen(modifier: Modifier = Modifier, onSaved: (Double) -> Unit) {
    var amount by remember {
        mutableStateOf("")
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 14.dp)
        ) {
            NumberTextField(
                modifier = Modifier.fillMaxWidth().height(35.dp).weight(1F),
                amountEntered = {
                    amount
                },
                onUpdate = {
                    amount = it
                })
            IconButton(
                onClick = {},
                modifier = Modifier.padding(end = 6.dp).clip(CircleShape)
                    .background(GreenTealColor.copy(0.7F))
                    .size(40.dp)
            ) {
                Icon(Icons.Filled.Check, contentDescription = "", tint = Color.White)
            }
        }
    }
}
