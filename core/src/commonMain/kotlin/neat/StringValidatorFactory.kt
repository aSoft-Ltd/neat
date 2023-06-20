@file:Suppress("NOTHING_TO_INLINE")

package neat

import kotlin.reflect.KProperty

fun string(label: String = "unnamed"): Validators<String> = custom(label)

fun <T> string(name: KProperty<T>) = validator<T>(name.name)

@PublishedApi
internal object Key {
    private const val root = "neat.string"
    const val LENGTH = "$root.length"
    const val MIN = "$root.min"
    const val MAX = "$root.max"
    const val NOT_BLANK = "$root.not.blank"
}

fun <T : String?, V : Validators<T>> V.length(
    value: Int,
    message: (value: String) -> String = { "$label should have $value character(s), but $it has ${it.length} character(s) instead" }
) = append(Key.LENGTH, value) { if (it.length == value) Valid(it) else Invalid(it, listOf(message(it))) }

inline val <T : String?> Validator<T>.length: Int? get() = int(Key.LENGTH)

fun <T : String?, V : Validators<T>> V.min(
    value: Int,
    message: (String) -> String = { "$label should have a more than $value character(s), but $it has ${it.length} character(s) instead" }
) = append(Key.MIN, value) {
    if (it.length >= value) Valid(it) else Invalid(it, listOf(message(it)))
}

inline val <T : String?> Validator<T>.min get() = int(Key.MIN)

fun <T : String?, V : Validators<T>> V.max(
    value: Int,
    message: (String) -> String = { "$label should have less than $value character(s), but $it has ${it.length} character(s) instead" }
) = append(Key.MAX, value) {
    if (it.length <= value) Valid(it) else Invalid(it, listOf(message(it)))
}

inline val <T : String?> Validator<T>.max get() = int(Key.MAX)

fun <T : String?, V : Validators<T>> V.notBlank(
    message: (String) -> String = { "$label is required to not be empty but it was" }
) = append(Key.NOT_BLANK) { if (it.isNotBlank()) Valid(it) else Invalid(it, listOf(message(it))) }