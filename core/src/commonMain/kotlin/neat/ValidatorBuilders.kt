@file:Suppress("NOTHING_TO_INLINE")

package neat

import neat.internal.ValidatingFunction
import neat.internal.OptionalValidator
import neat.internal.RequiredValidator

fun <T> Validator<T>.root(key: String): ValidatingFunction<*, *>? {
    val optionals = (this as? OptionalValidator<*>)?.validators?.all ?: emptyMap()
    val required = (this as? RequiredValidator<*>)?.validators?.all ?: emptyMap()
    return buildMap {
        putAll(optionals)
        putAll(required)
    }[key]
}

inline fun Validator<*>.int(key: String) = root(key)?.metadata as? Int

inline fun <T> Validator<T>.set(builder: Validator<T>.() -> Validator<T>): Validator<T> = builder()

inline val Validator<*>.required get() = this is RequiredValidator
inline val Validator<*>.optional get() = this is OptionalValidator

fun <T> Validators<T>.execute(
    function: (T & Any) -> Validity<T>
) = append("neat.execute", function)

fun <T> Validators<T>.check(
    message: (T & Any) -> String = { "Did not pass the check validation" },
    predicate: (T & Any) -> Boolean
) = append("neat.check") {
    if (predicate(it)) Valid(it) else Invalid(it, listOf(message(it)))
}

fun <T> custom(label: String) = Validators<T>(label, mutableMapOf())

fun <T> validator(label: String) = Validators<T>(label, mutableMapOf())