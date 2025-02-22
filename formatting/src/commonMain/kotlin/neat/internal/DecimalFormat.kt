package neat.internal

import kotlin.math.floor
import kotlin.math.pow

class DecimalFormat(private val pattern: String) {

    fun format(number: Double): String {
        val commas = pattern.contains(",")

        val tokens = pattern.split(".")
        val hasDot = pattern.contains(".")

        val decimalPart = when(hasDot) {
            true -> tokens[1]
            false -> ""
        }.replace("0", "#")
            .replace(regex=Regex("[^#]"), replacement = "")

        val integerPart = when(hasDot) {
            true -> tokens[0]
            false -> pattern
        }.replace(regex=Regex("[^#]"), replacement = "")

        val numDecimals = decimalPart.length

//        val value = roundToNDecimalPlaces(number, numDecimals)
        val value = when (pattern.contains("'%'")) {
            true -> number
            false -> when (pattern.contains("%")) {
                true -> number * 100.0
                false -> number
            }
        }

        val formattedNumber = when(commas) {
            true -> formatNumberWithCommas(value, numDecimals)
            false -> formatNumberWithCommas(value, numDecimals)
        }

        val hashes = when(numDecimals == 0) {
            true -> integerPart
            false -> integerPart + "." + decimalPart
        }

        val replacer = pattern
            .replace("0", "#")
            .replace(",#", "#")
            .replace("#,", "#")
            .replace(".#", "#")
            .replace(Regex("#+"), "#")

        val subFinal = replacer.replace("#", formattedNumber)

        return subFinal.replace("'%'", "%")
    }
}


fun formatNumberWithCommas(number: Double, decimals:Int): String {
    val value = roundToNDecimalPlaces(number, decimals)
//    val numberParts = number.toString().split(".")
    val _integerPart = floor(value).toLong()
    val _decimalPart = value - _integerPart
    val integerPart = _integerPart.toString()
    val decimalPart = _decimalPart.toString()
        .replace("0.", "")
        .substring(0, decimals)
//    val decimalPart = if (numberParts.size > 1) "." + numberParts[1] else ""

    val length = integerPart.length
    val stringBuilder = StringBuilder()

    for (i in 0 until length) {
        stringBuilder.append(integerPart[i])
        if ((length - i - 1) % 3 == 0 && i != length - 1) {
            stringBuilder.append(',')
        }
    }

    return stringBuilder.toString() + when (decimalPart.isEmpty() || decimalPart.equals("0")) {
        true -> ""
        false -> ".$decimalPart"
    }
}

private fun roundToNDecimalPlaces(number: Double, decimalPlaces: Int): Double {
    require(decimalPlaces >= 0) { "Decimal places must be non-negative" }

    val factor = 10.0.pow(decimalPlaces.toDouble())
    return (number * factor).toLong() / factor
}

//fun formatNumber(number: Double): String {
//    val formattedNumber = format("%.2f", number) // Format number with up to 2 decimal places
//    val parts = formattedNumber.split('.')
//    val integerPart = parts[0]
//    val decimalPart = if (parts.size > 1) parts[1].trimEnd('0').takeIf { it.isNotEmpty() } else ""
//
//    val integerBuilder = StringBuilder()
//    var count = 0
//    for (i in integerPart.length - 1 downTo 0) {
//        integerBuilder.insert(0, integerPart[i])
//        count++
//        if (count == 3 && i != 0) {
//            integerBuilder.insert(0, ',')
//            count = 0
//        }
//    }
//
//    val formattedInteger = integerBuilder.toString()
//    return if (decimalPart.isNullOrEmpty()) {
//        formattedInteger
//    } else {
//        "$formattedInteger.${decimalPart}"
//    }
//}