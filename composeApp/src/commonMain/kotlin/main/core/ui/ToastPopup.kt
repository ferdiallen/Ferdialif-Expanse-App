package main.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

data class ToastPopupData(
    val message: String,
    val icon: ImageVector? = null,
    val duration: Long = 0L,
    val shouldShow: Boolean = false
)

class MoneyPopup {
    var currentData: ToastPopupData? by mutableStateOf(ToastPopupData("", shouldShow = false))
        private set

    fun updateNewestData(data: ToastPopupData?) {
        currentData = data
    }
}

@Composable
fun rememberMoneyPopup(): MoneyPopup = rememberSaveable {
    MoneyPopup()
}

@Composable
fun ToastPopup(modifier: Modifier = Modifier, state: MoneyPopup, content: @Composable () -> Unit) {
    LaunchedEffect(state.currentData) {
        delay(state.currentData?.duration ?: 0L)
        state.updateNewestData(state.currentData?.copy(shouldShow = false))
    }
    Box(modifier, contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier.fillMaxSize()) {
            content()
        }
        AnimatedVisibility(
            state.currentData?.shouldShow ?: return, enter = fadeIn(
                tween(200)
            ) + scaleIn(tween(200)), exit = fadeOut(tween(400)) + scaleOut(tween(400))
        ) {
            Card(modifier = Modifier.padding(bottom = 24.dp), shape = CircleShape) {
                Column(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(state.currentData?.icon ?: return@Column, contentDescription = "")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(state.currentData?.message ?: "")
                    }
                }
            }
        }
    }
}