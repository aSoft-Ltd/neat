package formatter

import formatter.internal.NumberFormatterImpl
import kotlin.jvm.JvmOverloads

@Deprecated("use liquid instead")
fun NumberFormatter(
    abbreviate: Boolean = NumberFormatterOptions.DEFAULT_ABBREVIATE,
    prefix: String = NumberFormatterOptions.DEFAULT_PREFIX,
    postfix: String = NumberFormatterOptions.DEFAULT_POSTFIX,
    decimals: Int? = null,
    enforceDecimals: Boolean = NumberFormatterOptions.DEFAULT_ENFORCE_DECIMALS,
    decimalSeparator: String = NumberFormatterOptions.DEFAULT_DECIMAL_SEPARATOR,
    thousandsSeparator: String = NumberFormatterOptions.DEFAULT_THOUSAND_SEPERATOR
) = NumberFormatter(
    NumberFormatterOptions(
        abbreviate,
        prefix,
        postfix,
        decimals = decimals ?: if (abbreviate) NumberFormatterOptions.DEFAULT_DECIMALS_ABBREVIATED else NumberFormatterOptions.DEFAULT_DECIMALS_UNABBREVIATED,
        enforceDecimals,
        decimalSeparator,
        thousandsSeparator
    )
)

@JvmOverloads
inline fun NumberFormatter(
    options: NumberFormatterRawOptions = NumberFormatterOptions()
): NumberFormatter = NumberFormatterImpl(options)