package neat

import neat.internal.RequiredValidator
import kotlin.reflect.KProperty0

fun <T> Validators<T>.required(
    message: String = "$label is required, but was null"
): Validator<T> = RequiredValidator(this, message)

fun <P, C : Any> PropertyValidators<P, C>.required(
    message: String = "$label is required, but was null"
): Validator<C> {
    parent.properties.add(this)
    return RequiredValidator(this, message)
}

val <T : Any> KProperty0<T?>.required get() = get() ?: throw IllegalArgumentException("$name is required but was null")