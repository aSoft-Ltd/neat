package neat

import neat.internal.OptionalValidator
import neat.internal.RequiredValidator
import neat.internal.ValidatingFunction

class Validators<T>(
    val label: String,
    internal val all: MutableMap<String, ValidatingFunction<T, *>>
) {
    fun append(
        key: String,
        validator: (T & Any) -> Validity<T>
    ): Validators<T> = append(key, Unit, validator)

    fun <D> append(
        key: String,
        metadata: D,
        validator: (T & Any) -> Validity<T>
    ): Validators<T> {
        all[key] = ValidatingFunction(validator, metadata)
        return this
    }

    fun configure(validator: (Validators<T>.() -> Validator<T>)?): Validator<T> {
        if (validator == null) return OptionalValidator(this)
        return validator()
    }

    fun required(
        message: (T) -> String = { "$label is required, but was null" }
    ): Validator<T & Any> = RequiredValidator(this as Validators<T & Any>, message)

    fun optional(): Validator<T?> = OptionalValidator(this)
}