package neat

import neat.internal.OptionalValidator
import neat.internal.RequiredValidator
import neat.internal.ValidatingFunction

fun <T> Validator<T>.root(key: String): ValidatingFunction<*, *>? {
    val optionals = (this as? OptionalValidator<*>)?.validators?.functions ?: emptyMap()
    val required = (this as? RequiredValidator<*>)?.validators?.functions ?: emptyMap()
    return buildMap {
        putAll(optionals)
        putAll(required)
    }[key]
}

inline fun Validator<*>.int(key: String) = root(key)?.metadata as? Int

inline fun <T> Validator<T>.set(builder: Validator<T>.() -> Validator<T>): Validator<T> = builder()

inline val Validator<*>.required get() = this is RequiredValidator<*>
inline val Validator<*>.optional get() = this is OptionalValidator<*>