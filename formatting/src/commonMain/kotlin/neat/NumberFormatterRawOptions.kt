@file:Suppress("WRONG_EXPORTED_DECLARATION")

package neat

import neat.NumberFormatterOptions.Companion.DEFAULT_ABBREVIATE
import neat.NumberFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import neat.NumberFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import neat.NumberFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import neat.NumberFormatterOptions.Companion.DEFAULT_POSTFIX
import neat.NumberFormatterOptions.Companion.DEFAULT_PREFIX
import neat.NumberFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
import kotlinx.JsExport

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