package neat

data class Valid<out T>(
    override val value: T
) : Validity<T> {
    override fun getOrThrow(): T = value
    override fun <R> map(transformer: (T) -> R) = Valid(transformer(value))
}