@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package neat

import kotlin.js.JsName

interface NumberFormatter {
    val options: NumberFormatterRawOptions

    @JsName("_ignore_formatNumber")
    fun format(number: Number): String

    @JsName("formatNumber")
    fun format(number: Double): String
}