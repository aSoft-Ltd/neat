package liquid

import liquid.NumberFormatterOptions.Companion.DEFAULT_ABBREVIATE
import liquid.NumberFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import liquid.NumberFormatterOptions.Companion.DEFAULT_DECIMALS_UNABBREVIATED
import liquid.NumberFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import liquid.NumberFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import liquid.NumberFormatterOptions.Companion.DEFAULT_POSTFIX
import liquid.NumberFormatterOptions.Companion.DEFAULT_PREFIX
import liquid.NumberFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
import kotlin.jvm.JvmOverloads

@JvmOverloads
fun Number.format(
    abbreviate: Boolean = DEFAULT_ABBREVIATE,
    prefix: String = DEFAULT_PREFIX,
    postfix: String = DEFAULT_POSTFIX,
    decimals: Int? = null,
    enforceDecimals: Boolean = DEFAULT_ENFORCE_DECIMALS,
    decimalSeparator: String = DEFAULT_DECIMAL_SEPARATOR,
    thousandsSeparator: String = DEFAULT_THOUSAND_SEPERATOR
) = NumberFormatter(
    abbreviate,
    prefix,
    postfix,
    decimals = decimals ?: if (abbreviate) DEFAULT_DECIMALS_ABBREVIATED else DEFAULT_DECIMALS_UNABBREVIATED,
    enforceDecimals,
    decimalSeparator,
    thousandsSeparator
).format(this)