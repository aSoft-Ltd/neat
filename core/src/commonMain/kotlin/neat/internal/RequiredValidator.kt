package neat.internal

import neat.Invalid
import neat.Validator
import neat.Validators
import neat.Validity
import neat.aggregate

@PublishedApi
internal class RequiredValidator<T : Any>(
    internal val validators: Validators<T>,
    val message: (T) -> String = { "${validators.label} is required, but was null" }
) : Validator<T> {
    override val label: String = validators.label
    override fun validate(value: T): Validity<T> = when (value) {
        null -> Invalid(value, listOf(message(value)))
        else -> validators.all.values.map {
            it.function(value)
        }.aggregate(value)
    }
}