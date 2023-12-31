package neat

import neat.internal.OptionalValidator
import kotlin.reflect.KProperty1

class PropertyValidators<P, C>(
    label: String,
    val parent: CompoundValidators<P>,
    val nested: CompoundValidators<P>?,
    val property: KProperty1<P, C>
) : Validators<C>(label) {

//    override fun required(
//        message: (C) -> String
//    ): Validator<C> {
//        parent.properties.add(this)
//        return RequiredValidator(this as Validators<C & Any>, message) as Validator<C>
//    }

    override fun optional(): Validator<C?> {
        parent.properties.add(this)
        return OptionalValidator(this)
    }
}