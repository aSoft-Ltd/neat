package neat

import neat.NumberFormatterOptions.Companion.DEFAULT_ABBREVIATE
import neat.NumberFormatterOptions.Companion.DEFAULT_DECIMALS_ABBREVIATED
import neat.NumberFormatterOptions.Companion.DEFAULT_DECIMALS_UNABBREVIATED
import neat.NumberFormatterOptions.Companion.DEFAULT_DECIMAL_SEPARATOR
import neat.NumberFormatterOptions.Companion.DEFAULT_ENFORCE_DECIMALS
import neat.NumberFormatterOptions.Companion.DEFAULT_POSTFIX
import neat.NumberFormatterOptions.Companion.DEFAULT_PREFIX
import neat.NumberFormatterOptions.Companion.DEFAULT_THOUSAND_SEPERATOR
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