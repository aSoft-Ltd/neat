package neat.internal

import neat.CompoundValidators
import neat.Invalid
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
    override fun validate(value: P): Validity<P> = buildList {
        @Suppress("SENSELESS_COMPARISON") // if called in a JS environment, it can be null
        if (value == null) {
            add(Invalid(value, listOf(message(value))))
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
    }.aggregate(value)
}