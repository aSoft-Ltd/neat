@file:Suppress("NOTHING_TO_INLINE")

package neat.collections

import neat.Invalid
import neat.Valid
import neat.Validator
import neat.Validators
import neat.append
import neat.int

@PublishedApi
internal object CollectionKey {
    private const val root = "neat.collection"
    const val SIZE = "$root.length"
    const val MIN = "$root.min"
    const val MAX = "$root.max"
}

fun <T : Collection<*>, V : Validators<T>> V.size(
    value: Int,
    message: (T) -> String = { "$label collection should have $value item(s), but has ${it.size} items(s) instead" }
) = append(CollectionKey.SIZE, value) {
    if (it.size == value) Valid(it) else Invalid(it, listOf(message(it)))
}

inline val <T : Collection<*>> Validator<T>.size get() = int(CollectionKey.SIZE)

fun <T : Collection<*>, V : Validators<T>> V.min(
    value: Int,
    message: (T) -> String = { "$label collection should have more than $value item(s), but has ${it.size} items(s) instead" }
) = append(CollectionKey.MIN, value) {
    if (it.size >= value) Valid(it) else Invalid(it, listOf(message(it)))
}

inline val <T : Collection<*>> Validator<T>.min get() = int(CollectionKey.MIN)

fun <T : Collection<*>, V : Validators<T>> V.max(
    value: Int,
    message: (T) -> String = { "$label collection should have less than $value item(s), but has ${it.size} item(s) instead" }
) = append(CollectionKey.MAX, value) {
    if (it.size <= value) Valid(it) else Invalid(it, listOf(message(it)))
}

inline val <T : Collection<*>> Validator<T>.max get() = int(CollectionKey.MAX)