package neat

import neat.exceptions.ValidityException

data class Invalid<out T>(
    override val value: T,
    val reasons: List<String>
) : Validity<T> {

    constructor(value: T, reason: String) : this(value, listOf(reason))

    fun exception(): ValidityException {
        val size = reasons.size
        val terminator = "error" + if (size > 1) "s" else ""
        return ValidityException(
            message = "You have $size validation $terminator",
            reasons = reasons,
            details = buildString {
                appendLine("Validation Error(s)")
                reasons.forEachIndexed { idx, it ->
                    appendLine("\t${idx + 1}. $it")
                }
            }
        )
    }

    override fun getOrThrow(): T = throw exception()

    override fun <R> map(transformer: (T) -> R) = Invalid(transformer(value), reasons)
}