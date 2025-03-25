package org.ferdi.money

import App
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ferdialifcountermoney.composeapp.generated.resources.Res
import ferdialifcountermoney.composeapp.generated.resources.app_name
import org.jetbrains.compose.reload.DevelopmentEntryPoint
import org.jetbrains.compose.resources.stringResource


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = rememberWindowState(
            placement = WindowPlacement.Floating,
            position = WindowPosition(Alignment.TopEnd),
            width = 360.dp,
            height = 700.dp
        ),
        alwaysOnTop = true
    ) {
        DevelopmentEntryPoint {
            App(modifier = Modifier.fillMaxSize())
        }
    }
}
