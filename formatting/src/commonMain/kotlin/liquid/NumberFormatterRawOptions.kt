@file:Suppress("WRONG_EXPORTED_DECLARATION")

package liquid

import liquid.NumberFormatterOptions.Companion.DEFAULT_ABBREVIATE
import liquid.NumberFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import liquid.NumberFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import liquid.NumberFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import liquid.NumberFormatterOptions.Companion.DEFAULT_POSTFIX
import liquid.NumberFormatterOptions.Companion.DEFAULT_PREFIX
import liquid.NumberFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR

@JsExport
interface NumberFormatterRawOptions {
    val abbreviate: Boolean?
    val prefix: String?
    val postfix: String?
    val decimals: Int?
    val enforceDecimals: Boolean?
    val decimalSeparator: String?
    val thousandsSeparator: String?
}

fun NumberFormatterRawOptions.toFormatterOptions() = NumberFormatterOptions(
    abbreviate = abbreviate ?: DEFAULT_ABBREVIATE,
    prefix = prefix ?: DEFAULT_PREFIX,
    postfix = postfix ?: DEFAULT_POSTFIX,
    decimals = decimals ?: DEFAULT_DECIMALS_ABBREVIATED,
    enforceDecimals = enforceDecimals ?: DEFAULT_ENFORCE_DECIMALS,
    decimalSeparator = decimalSeparator ?: DEFAULT_DECIMAL_SEPARATOR,
    thousandsSeparator = thousandsSeparator ?: DEFAULT_THOUSAND_SEPERATOR
)