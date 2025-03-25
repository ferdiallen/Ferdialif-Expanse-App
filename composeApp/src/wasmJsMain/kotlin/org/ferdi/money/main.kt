package org.ferdi.money

import App
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.ComposeViewport
import ferdialifcountermoney.composeapp.generated.resources.Res
import ferdialifcountermoney.composeapp.generated.resources.regular
import kotlinx.browser.document
import main.core.font.poppinsFont
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.preloadFont
import org.kodein.emoji.compose.EmojiService

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        var fontReady by remember {
            mutableStateOf(false)
        }
       val fontResolver = LocalFontFamilyResolver.current
        val poppinsFont = poppinsFont()
        val sampleFont by preloadFont(Res.font.regular, weight = FontWeight.Normal, FontStyle.Normal)
        LaunchedEffect(sampleFont) {
            if (sampleFont != null) {
                fontReady = true
            }
        }
        LaunchedEffect(Unit) {
            EmojiService.initialize()
            fontResolver.preload(poppinsFont)
            appLoaded()
        }
        if (fontReady) {
            App(modifier = Modifier.fillMaxSize())
        }
    }
}

external fun appHasLoaded()

fun appLoaded() {
    appHasLoaded()
}