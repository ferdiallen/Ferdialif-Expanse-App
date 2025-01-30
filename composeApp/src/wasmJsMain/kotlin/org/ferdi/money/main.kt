package org.ferdi.money

import App
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.kodein.emoji.compose.EmojiService

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        LaunchedEffect(Unit) {
            EmojiService.initialize()
            appLoaded()
        }
        App(modifier = Modifier.fillMaxSize())
    }
}

external fun appHasLoaded()

fun appLoaded() {
    appHasLoaded()
}