package com.example.smarttask.utility;

import java.text.SimpleDateFormat
import java.util.*

val myFormat = "dd.MM.yyyy" // mention the format you need
val sdf = SimpleDateFormat(myFormat)

fun formatDate(date: Date, formatPattern: String = myFormat): String {
    sdf.applyPattern(formatPattern)

    return sdf.format(date)
}