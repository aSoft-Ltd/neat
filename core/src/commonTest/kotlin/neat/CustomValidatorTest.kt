package neat

import kommander.expect
import kommander.toBe
import kotlin.test.Test

class CustomValidatorTest {

    data class Person(val name: String, val age: String?)

    object PersonValidator : Validator<Person> {
        override val label = "Person"
        val name = string(Person::name).notBlank().required()
        val age = string(Person::age).optional()
        override fun validate(value: Person): Validity<Person> = listOf(
            name.validate(value.name),
            age.validate(value.age)
        ).aggregate(value)
    }

    @Test
    fun should_create_a_customer_validator() {
        val res = expect(PersonValidator.validate(Person("", ""))).toBe<Invalid<Person>>()
        expect(res.reasons.first()).toBe("name is required to not be empty but it was")
    }

    fun person(): Validator<Person> = custom<Person>("person").optional()
}