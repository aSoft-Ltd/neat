@file:Suppress("NOTHING_TO_INLINE")

package neat

import neat.internal.ValidatingFunction

fun <T, V : Validators<T>> V.execute(
    function: (T & Any) -> Validity<T>
) = append("neat.execute", function)

fun <T, V : Validators<T>> V.check(
    message: (T & Any) -> String = { "Did not pass the check validation" },
    predicate: (T & Any) -> Boolean
) = append("neat.check") {
    if (predicate(it)) Valid(it) else Invalid(it, listOf(message(it)))
}

fun <T, D, V : Validators<T>> V.append(
    key: String,
    metadata: D,
    validator: (T & Any) -> Validity<T>
): V {
    functions[key] = ValidatingFunction(validator, metadata)
    return this
}

fun <T, V : Validators<T>> V.append(
    key: String,
    validator: (T & Any) -> Validity<T>
) = append(key, Unit, validator)

fun <T> custom(label: String) = Validators<T>(label)

fun <T> validator(label: String) = Validators<T>(label)