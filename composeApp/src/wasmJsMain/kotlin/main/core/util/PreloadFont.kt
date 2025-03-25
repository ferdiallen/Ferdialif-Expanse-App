package main.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.FontResource
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/*
private val emptyFont =
    "T1RUTwAJAIAAAwAQQ0ZGIML7MfIAAAQIAAAA2U9TLzJmMV8PAAABAAAAAGBjbWFwANUAVwAAA6QAAABEaGVhZCMuU7" +
            "IAAACcAAAANmhoZWECvgAmAAAA1AAAACRobXR4Az4AAAAABOQAAAAQbWF4cAAEUAAAAAD4AAAABm5hbWUpw3nbAAABYAAAAkNwb3N0AAMA" +
            "AAAAA+gAAAAgAAEAAAABAADs7nftXw889QADA+gAAAAA4WWJaQAAAADhZYlpAAAAAAFNAAAAAAADAAIAAAAAAAAAAQAAArz+1AAAAU0AAA"


@OptIn(ExperimentalEncodingApi::class)
private val defaultEmptyFont by lazy {
    androidx.compose.ui.text.platform.Font("org.jetbrains.compose.emptyFont",Base64.decode(emptyFont))
}

@Composable
fun preloadFont(resourceFont: FontResource,weight: FontWeight,style: FontStyle):State<Font?>{
    val state = remember (resourceFont,weight,style){
        mutableStateOf<Font?>(null)
    }.apply {
        value = org.jetbrains.compose.resources.Font(resourceFont,weight,style).takeIf {
            it != defaultEmptyFont
        }
    }
    return state
}*/
