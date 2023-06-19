package neat.exceptions

class ValidityException(
    override val message: String,
    val reasons: List<String>
) : Throwable(message)