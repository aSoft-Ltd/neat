package neat

import neat.internal.OptionalValidator
import neat.internal.ValidatingFunction

open class Validators<T>(val label: String) {

    internal val functions = mutableMapOf<String, ValidatingFunction<T, *>>()

    fun configure(validator: ValidationFactory<T>?): Validator<T> {
        if (validator == null) return optional()
        return validator()
    }

    open fun optional(): Validator<T?> = OptionalValidator(this)
}