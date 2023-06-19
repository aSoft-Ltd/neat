@file:Suppress("NOTHING_TO_INLINE")

package neat

import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.reflect.KProperty

fun string(label: String = "unnamed"): Validator<String> = custom(label)

@JsName("optionalString")
@JvmName("optionalString")
fun <T : Any> string(name: KProperty<T?>) = string(name.name).optional()

fun <T : Any> string(name: KProperty<T>) = string(name.name).required()

private object Key {
    private const val root = "neat.string"
    const val LENGTH = "$root.length"
    const val MIN = "$root.min"
    const val MAX = "$root.max"
    const val NOT_BLANK = "$root.not.blank"
}

fun Validator<String>.length(
    value: Int,
    message: (value: String) -> String = { "$label should have $value character(s), but $it has ${it.length} character(s) instead" }
) = append(Key.LENGTH, value) { if (it.length == value) Valid(it) else Invalid(it, listOf(message(it))) }

val Validator<String>.length: Int? get() = int(Key.LENGTH)

fun Validator<String>.min(
    value: Int,
    message: (String) -> String = { "$label should have a more than $value character(s), but $it has ${it.length} character(s) instead" }
) = append(Key.MIN, value) {
    if (it.length >= value) Valid(it) else Invalid(it, listOf(message(it)))
}

val Validator<String>.min get() = int(Key.MIN)

fun Validator<String>.max(
    value: Int,
    message: (String) -> String = { "$label should have less than $value character(s), but $it has ${it.length} character(s) instead" }
) = append(Key.MAX, value) {
    if (it.length <= value) Valid(it) else Invalid(it, listOf(message(it)))
}

val Validator<String>.max get() = int(Key.MAX)

fun Validator<String>.notBlank(
    message: (String) -> String = { "$label is required to not be empty but it was" }
) = append(Key.NOT_BLANK) { if (it.isNotBlank()) Valid(it) else Invalid(it, listOf(message(it))) }