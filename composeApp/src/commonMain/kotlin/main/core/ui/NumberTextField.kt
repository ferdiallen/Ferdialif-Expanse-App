package main.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest
import main.core.font.poppinsFont
import main.core.util.TransformDataMoney
import main.core.util.currencyResult

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    amountEntered: () -> String,
    onUpdate: (String) -> Unit
) {
    val isNotNumber = remember(amountEntered()) {
        snapshotFlow { amountEntered() }
            .mapLatest { result ->
                if (result.contains(" ")) {
                    true
                } else {
                    result.all { !it.isDigit() }
                }
            }
    }.collectAsStateWithLifecycle(null)
    LaunchedEffect(isNotNumber.value) {
        if (isNotNumber.value == true) {
            onUpdate("")
        }
    }
    BasicTextField(
        value = amountEntered(),
        onValueChange = {
            onUpdate(it)
        },
        modifier = modifier,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFont()
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(
                modifier = Modifier
                    .padding(start = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (amountEntered().isEmpty()) {
                        Text(
                            currencyResult(5000),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.LightGray,
                            modifier = Modifier.fillMaxSize()
                                .padding(top = 6.dp)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        it()
                    }

                }
            }
        },
        visualTransformation = TransformDataMoney(),
        maxLines = 1,
        keyboardActions = KeyboardActions(onDone = {

        })
    )
}