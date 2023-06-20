package neat

import neat.internal.OptionalValidator
import neat.internal.RequiredValidator
import neat.internal.ValidatingFunction
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

open class Validators<T>(val label: String) {

    internal val functions = mutableMapOf<String, ValidatingFunction<T, *>>()

    fun configure(validator: (Validators<T>.() -> Validator<T>)?): Validator<T> {
        if (validator == null) return optional()
        return validator()
    }

    open fun optional(): Validator<T?> = OptionalValidator(this)
}