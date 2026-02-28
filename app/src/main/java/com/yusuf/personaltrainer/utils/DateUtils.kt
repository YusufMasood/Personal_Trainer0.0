package com.yusuf.personaltrainer.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {
    fun today(): String {
        return LocalDate.now()
            .format(DateTimeFormatter.ISO_DATE)
    }
}