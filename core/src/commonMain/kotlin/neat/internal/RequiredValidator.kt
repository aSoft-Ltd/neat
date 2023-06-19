package neat.internal

import neat.CompoundValidators
import neat.Validator
import neat.Validators
import neat.Validity
import neat.aggregate

@PublishedApi
internal class RequiredValidator<P : Any>(
    internal val validators: Validators<P>,
    val message: (P) -> String = { "${validators.label} is required, but was null" }
) : Validator<P> {
    override val label: String = validators.label
    override fun validate(value: P): Validity<P> = if (validators is CompoundValidators) {
        validateRecursively(
            validators = validators as CompoundValidators<Any?>,
            value = value
        ).aggregate(value)
    } else {
        validators.functions.values.map {
            it.function(value)
        }.aggregate(value)
    }
}