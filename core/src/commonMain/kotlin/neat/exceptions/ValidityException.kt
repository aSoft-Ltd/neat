package neat.exceptions

class ValidityException(
    override val message: String,
    val details: String,
    val reasons: List<String>
) : Throwable(message)