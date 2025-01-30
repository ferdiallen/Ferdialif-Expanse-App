package main.core.util

import java.text.NumberFormat
import java.util.Locale

actual fun currencyResult(data: Long): String {
    val result = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(data)
    return result
}