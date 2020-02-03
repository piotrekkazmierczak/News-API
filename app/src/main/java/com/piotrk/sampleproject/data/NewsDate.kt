package com.piotrk.sampleproject.data

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

typealias NewsDateTime = LocalDateTime

val YEAR_MONTH_DAY_HOUR_MIN_SEC_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)!!
val DAY_NAME_MONTH_NAME_DAY_FORMATTER = DateTimeFormatter.ofPattern("EE, LLL d - h:mm a", Locale.US)!!
