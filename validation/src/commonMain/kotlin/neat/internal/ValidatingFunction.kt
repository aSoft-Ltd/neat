package neat.internal

import neat.Validity

open class ValidatingFunction<T, D>(
    val function: (T & Any) -> Validity<T>,
    val metadata: D
)