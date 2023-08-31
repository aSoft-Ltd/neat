@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package formatter

import kotlin.js.JsName
import liquid.JsExport

@Deprecated("use liquid instead")
interface NumberFormatter {
    val options: NumberFormatterRawOptions

    @JsName("_ignore_formatNumber")
    fun format(number: Number): String

    @JsName("formatNumber")
    fun format(number: Double): String
}