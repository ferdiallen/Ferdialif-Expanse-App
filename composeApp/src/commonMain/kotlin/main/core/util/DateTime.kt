package main.core.util

import kotlinx.datetime.Clock
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.toLocalDateTime

@OptIn(FormatStringsInDatetimeFormats::class)
fun retrieveCurrentDate(): String {
    val clock = Clock.System.now()
    val result = clock.toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
    val date = result
    return "${date.dayOfMonth} ${
        date.month.name.mapIndexed {index,char->
           return@mapIndexed if(index > 0){
                char.lowercaseChar()
            }else {
                char
           }
        }.joinToString("")}, ${ date.year } at ${ result.hour }:${ result.minute }"
    }