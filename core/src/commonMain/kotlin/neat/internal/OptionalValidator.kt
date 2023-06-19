package neat.internal

import neat.Valid
import neat.Validator
import neat.Validators
import neat.Validity
import neat.aggregate

@PublishedApi
internal class OptionalValidator<T>(
    internal val validators: Validators<T>
) : Validator<T?> {
    override val label: String = validators.label
    override fun validate(value: T?): Validity<T?> = when (value) {
        null -> Valid(value)
        else -> validators.all.values.map {
            it.function(value)
        }.aggregate(value)
    }
}