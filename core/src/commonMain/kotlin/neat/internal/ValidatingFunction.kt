package neat.internal

import neat.Validity

class ValidatingFunction<T, D>(
    val function: (T & Any) -> Validity<T>,
    val metadata: D
)