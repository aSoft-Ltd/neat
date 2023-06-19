package neat.internal

import neat.CompoundValidators
import neat.Valid
import neat.Validator
import neat.Validators
import neat.Validity
import neat.aggregate

@PublishedApi
internal class OptionalValidator<P>(
    internal val validators: Validators<P>,
) : Validator<P?> {
    override val label: String = validators.label
    override fun validate(value: P?): Validity<P?> {
        if (value == null) return Valid(value)

        if (validators is CompoundValidators) {
            return validateRecursively(
                validators = validators as CompoundValidators<Any?>,
                value = value
            ).aggregate(value)
        }

        return validators.functions.values.map {
            it.function(value)
        }.aggregate(value)
    }
}