package com.niqr.worker.utils

fun refactorNum(num: String, max: Int?): String {
    if (num.isNotBlank() && num != ".")
        return num.toDouble().toString().let {
            if (max != null && it.toDouble() > max)
                max.toString()
            else if (it.takeLast(2) == ".0")
                it.toDouble().toInt().toString()
            else
                it
        }
    return ""
}