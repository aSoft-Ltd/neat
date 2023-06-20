package neat

import neat.internal.OptionalValidator
import neat.internal.RequiredValidator
import neat.internal.ValidatingFunction
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

open class Validators<T>(val label: String) {

    internal val functions = mutableMapOf<String, ValidatingFunction<T, *>>()

    fun append(
        key: String,
        validator: (T & Any) -> Validity<T>
    ): Validators<T> = append(key, Unit, validator)

    fun <D> append(
        key: String,
        metadata: D,
        validator: (T & Any) -> Validity<T>
    ): Validators<T> {
        functions[key] = ValidatingFunction(validator, metadata)
        return this
    }

    fun configure(validator: (Validators<T>.() -> Validator<T>)?): Validator<T> {
        if (validator == null) return optional()
        return validator()
    }

    open fun required(
        message: (T) -> String = { "$label is required, but was null" }
    ): Validator<T> = RequiredValidator(this as Validators<T & Any>, message) as Validator<T>

    open fun optional(): Validator<T?> = OptionalValidator(this)
}