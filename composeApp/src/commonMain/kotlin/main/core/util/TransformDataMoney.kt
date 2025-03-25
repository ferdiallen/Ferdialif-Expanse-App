package main.core.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TransformDataMoney : VisualTransformation {
    private fun currencyFormatMapping(formatted: String): OffsetMapping {
        return object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (formatted == "") {
                    0
                } else {
                    when (offset) {
                        in 1..3 -> offset + 3
                        in 4..6 -> offset + 4
                        in 7..9 -> offset + 5
                        else -> offset
                    }

                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                val lengthFormat = formatted.takeIf {
                    it != ""
                }?.filter {
                    it.isDigit()
                }?.length.run {
                    if (this == 0) {
                        0
                    } else {
                        if (this == null) {
                            return@run 0
                        }
                        this - 3
                    }
                }
                return lengthFormat
            }

        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val filteredText = text.text.filter {
            it.isDigit()
        }.takeIf {
            it != ""
        }
        val formattedText = if (text.text.isNotEmpty()) {
            filteredText?.toLongOrNull()?.let {
                currencyResult(it)

            }
        } else {
            ""
        }
        return TransformedText(
            AnnotatedString(formattedText ?: ""),
            currencyFormatMapping(formattedText ?: "")
        )
    }

}
