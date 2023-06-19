package neat

fun <T> Collection<Validity<*>>.aggregate(value: T): Validity<T> {
    val invalids = filterIsInstance<Invalid<*>>()
    return if (invalids.isEmpty()) {
        Valid(Unit)
    } else {
        Invalid(Unit, invalids.flatMap { it.reasons })
    }.map { value }
}