package neat.internal

import neat.Validator
import neat.Validity

@PublishedApi
internal class FunctionalValidator<T, D>(
    override val label: String,
    val metadata: D,
    val function: (T) -> Validity<T>
) : Validator<T> {
    override fun validate(value: T): Validity<T> = function(value)
}