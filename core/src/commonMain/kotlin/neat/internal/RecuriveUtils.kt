package neat.internal

import neat.CompoundValidators
import neat.Validity

fun validateRecursively(validators: CompoundValidators<Any?>, value: Any?): List<Validity<*>> = validators.properties.flatMap { propertyValidator ->
    val cValue = propertyValidator.propery.get(value)

    println("Validating ${propertyValidator.propery.name} = $value")
    val nested = propertyValidator.nested
    val nesteds = if (nested == null) emptyList() else validateRecursively(nested, cValue)

    val properties = propertyValidator.functions.values.map {
        val function = it.function as (Any?) -> Validity<Any>
        function(cValue)
    }

    nesteds + properties
}