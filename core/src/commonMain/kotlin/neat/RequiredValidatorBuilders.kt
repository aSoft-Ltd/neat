package neat

import neat.internal.RequiredValidator

fun <T : Any> Validators<T>.required(
    message: (T) -> String = { "$label is required, but was null" }
): Validator<T> = RequiredValidator(this, message)

fun <P, C : Any> PropertyValidators<P, C>.required(
    message: (C) -> String = { "$label is required, but was null" }
): Validator<C> {
    parent.properties.add(this)
    return RequiredValidator(this, message)
}