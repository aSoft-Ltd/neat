package neat

class Validators<T>(
    override val label: String,
    val all: MutableMap<String, Validator<T>>
) : Validator<T> {
    override fun validate(value: T): Validity<T> = all.values.map {
        it.validate(value)
    }.aggregate(value)
}