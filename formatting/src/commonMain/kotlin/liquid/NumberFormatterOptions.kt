package liquid

import kotlin.jvm.JvmField
import kotlin.math.pow

data class NumberFormatterOptions(
    override val abbreviate: Boolean = DEFAULT_ABBREVIATE,
    override val prefix: String = DEFAULT_PREFIX,
    override val postfix: String = DEFAULT_POSTFIX,
    override val decimals: Int = DEFAULT_DECIMALS_ABBREVIATED,
    override val enforceDecimals: Boolean = DEFAULT_ENFORCE_DECIMALS,
    override val decimalSeparator: String = DEFAULT_DECIMAL_SEPARATOR,
    override val thousandsSeparator: String = DEFAULT_THOUSAND_SEPERATOR
) : NumberFormatterRawOptions {

    val rounder by lazy { 10.0.pow(decimals) }

    companion object {
        @JvmField
        val DEFAULT_ABBREVIATE = false

        @JvmField
        val DEFAULT_PREFIX = ""

        @JvmField
        val DEFAULT_POSTFIX = ""

        @JvmField
        val DEFAULT_DECIMALS_ABBREVIATED = 1

        @JvmField
        val DEFAULT_ENFORCE_DECIMALS = false

        @JvmField
        val DEFAULT_DECIMALS_UNABBREVIATED = 2

        @JvmField
        val DEFAULT_DECIMAL_SEPARATOR = "."

        @JvmField
        val DEFAULT_THOUSAND_SEPERATOR = ","
    }
}