package neat.internal

import neat.CompoundValidators
import neat.Invalid
import neat.Validator
import neat.Validators
import neat.Validity
import neat.aggregate

@PublishedApi
internal class RequiredValidator<P>(val validators: Validators<P>, val message: String) : Validator<P> {
    override val label: String = validators.label
    override fun validate(value: P?): Validity<P> = buildList {
        if (value == null) {
            add(Invalid(value, listOf(message)))
            return@buildList
        }
        if (validators is CompoundValidators) {
            addAll(
                validateRecursively(
                    validators = validators as CompoundValidators<Any?>,
                    value = value
                )
            )
        } else {
            addAll(validators.functions.values.map {
                it.function(value)
            })
        }
    }.aggregate(value) as Validity<P>
}