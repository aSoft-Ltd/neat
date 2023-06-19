package neat

data class Invalid<out T>(
    override val value: T,
    val reasons: List<String>
) : Validity<T> {
    override fun <R> map(transformer: (T) -> R) = Invalid(transformer(value), reasons)
}