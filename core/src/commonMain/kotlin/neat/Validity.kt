package neat

sealed interface Validity<out T> {
    val value: T

    fun <R> map(transformer: (T) -> R): Validity<R>

    fun getOrThrow(): T
}