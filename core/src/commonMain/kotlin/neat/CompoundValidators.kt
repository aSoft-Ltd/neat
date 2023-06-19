package neat

import kotlin.reflect.KProperty1

open class CompoundValidators<P>(label: String) : Validators<P>(label) {

    internal val properties = mutableListOf<PropertyValidators<P, *>>()

    fun <C> KProperty1<P, C>.validator() = PropertyValidators("$label.${this.name}", this@CompoundValidators, null, this)

    //    fun <C, R> KProperty1<P, C>.validator(builder: (String) -> Validators<R>) = builder("$label ${this.name}")
    fun <C> KProperty1<P, C>.validator(
        builder: (String) -> Validators<C>
    ) = PropertyValidators("$label.${this.name}", this@CompoundValidators, builder("$label.${this.name}") as CompoundValidators<P>, this)
}