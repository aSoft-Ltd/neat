package neat.internal

import neat.NumberFormatter
import neat.NumberFormatterOptions
import neat.NumberFormatterRawOptions
import neat.toFormatterOptions
import kotlin.jvm.JvmOverloads
import kotlin.math.round

open class DecimalFormatterImpl @JvmOverloads constructor(
    val pattern:String,
    override val options: NumberFormatterRawOptions = NumberFormatterOptions()
) : NumberFormatter {
    override fun format(number: Number): String = DecimalFormat(pattern).format(number.toDouble())

    override fun format(number: Double): String = format(number as Number)
}