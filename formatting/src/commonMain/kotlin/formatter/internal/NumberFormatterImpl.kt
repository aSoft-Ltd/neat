package formatter.internal

import formatter.NumberFormatter
import formatter.NumberFormatterOptions
import formatter.NumberFormatterRawOptions
import formatter.toFormatterOptions
import kotlin.jvm.JvmOverloads
import kotlin.math.round

open class NumberFormatterImpl @JvmOverloads constructor(
    override val options: NumberFormatterRawOptions = NumberFormatterOptions()
) : NumberFormatter {
    private fun finalizeDecimals(amount: Double, options: NumberFormatterOptions): String {
        return if (!options.enforceDecimals) {
            if (amount.toString().endsWith(".0")) {
                return amount.toInt().toString()
            }
            amount.toString()
        } else {
            if (amount.toString().endsWith(".0")) {
                val mantisa = "0".repeat(options.decimals)
                return "${amount.toInt()}.$mantisa"
            } else {
                val mantisa = amount.toString().split(".").getOrNull(1)
                if (mantisa == null) {
                    "${amount}.${"0".repeat(options.decimals)}"
                } else {
                    val numberOfDecimals = amount.toString().split(".").getOrElse(1) { "" }.length
                    amount.toString() + "0".repeat(options.decimals - numberOfDecimals)
                }
            }
        }
    }

    override fun format(number: Number): String = if (options.abbreviate == true) {
        val opt = options.toFormatterOptions().copy(
            decimals = options.decimals ?: NumberFormatterOptions.DEFAULT_DECIMALS_ABBREVIATED
        )
        val value = number.toDouble()
        val (postfix, divider) = when {
            value < 1_000 -> "" to 1
            value >= 1_000 && value < 1_000_000 -> "K" to 1_000
            value >= 1_000_000 && value < 1_000_000_000 -> "M" to 1_000_000
            else -> "B" to 1_000_000_000
        }
        val rounded = round(value * opt.rounder / divider) / opt.rounder
        "${opt.prefix}${finalizeDecimals(rounded, opt)}$postfix"
    } else {
        val opt = options.toFormatterOptions().copy(
            decimals = options.decimals ?: NumberFormatterOptions.DEFAULT_DECIMALS_UNABBREVIATED
        )
        val value = round(number.toDouble() * opt.rounder) / opt.rounder
        val splits = value.toString().split('.')
        var characteristic = splits[0]
        var mantisa = splits.getOrElse(1) { "0" }
        mantisa = if (mantisa.toDouble() == 0.0) "" else mantisa
        characteristic = characteristic.toCharArray().toList().reversed()
            .chunked(3).reversed().joinToString(separator = opt.thousandsSeparator) {
                it.reversed().joinToString(separator = "")
            }
        val finalizedValue = finalizeDecimals(value, opt)
        val mts = finalizedValue.split(".").getOrElse(1) { "" }
        if (opt.enforceDecimals && mantisa == "") {
            "${opt.prefix}$characteristic.$mts${opt.postfix}"
        } else if (mantisa == "") {
            "${opt.prefix}$characteristic${opt.postfix}"
        } else {
            "${opt.prefix}$characteristic${opt.decimalSeparator}$mts${opt.postfix}"
        }
    }

    override fun format(number: Double): String = format(number as Number)
}