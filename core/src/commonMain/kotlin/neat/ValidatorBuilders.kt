@file:Suppress("NOTHING_TO_INLINE")

package neat

import neat.internal.FunctionalValidator
import neat.internal.OptionalValidator

fun <T : Any> Validator<T>.optional(): Validator<T?> = OptionalValidator(label, this)

fun <T> Validator<T>.append(
    key: String,
    validator: (T) -> Validity<T>
): Validator<T> {
    val container = this as? Validators ?: return this
    container.all[key] = FunctionalValidator(label, Unit, validator)
    return this
}

inline fun <T, D> Validator<T>.append(
    key: String,
    metadata: D,
    noinline validator: (T) -> Validity<T>
): Validator<T> = append(key, FunctionalValidator(label, metadata, validator))

fun <T> Validator<T>.append(
    key: String,
    validator: Validator<T>
): Validator<T> {
    val container = this as? Validators ?: return this
    container.all[key] = validator
    return this
}

fun <T> Validator<T>.root() = this as? Validators

fun <T> Validator<T>.root(key: String) = root()?.all?.get(key)

inline fun Validator<*>.int(key: String) = (root(key) as? FunctionalValidator<*, *>)?.metadata as? Int

inline fun <T> Validator<T>.set(builder: Validator<T>.() -> Validator<T>): Validator<T> = builder()

private const val VALIDATOR_KEY_REQUIRED = "neat.required"

fun <T> Validator<T>.required(
    message: (T) -> String = { "$label is required, but was null" }
) = append(VALIDATOR_KEY_REQUIRED) {
    if (it != null) Valid(it) else Invalid(it, listOf(message(it)))
}

private const val VALIDATOR_KEY_EXECUTE = "neat.execute"
fun <T> Validator<T>.execute(
    function: (T) -> Validity<T>
) = append(VALIDATOR_KEY_EXECUTE) { function(it) }

fun <T> custom(label: String): Validator<T> = Validators(label, mutableMapOf())

fun <T> validator(label: String): Validator<T> = Validators(label, mutableMapOf())