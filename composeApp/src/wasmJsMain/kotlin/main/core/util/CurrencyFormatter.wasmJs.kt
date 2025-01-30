package main.core.util

actual fun currencyResult(data: Long): String =
    js("new Intl.NumberFormat('id-ID', { style: 'currency', currency: 'IDR' }).format(data)")