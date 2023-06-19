package neat

import kommander.expect
import kommander.toBe
import kotlin.test.Test

class CustomValidatorTest {

    data class Person(val name: String, val age: String?)

    class PersonValidators(label: String) : CompoundValidators<Person>(label) {
        val name = Person::name.validator().notBlank().required()
        val age = Person::age.validator().optional()
    }

    @Test
    fun should_create_a_customer_validator() {
        val validator = person().optional()
        val res = expect(validator.validate(Person("", ""))).toBe<Invalid<Person>>()
        expect(res.reasons.first()).toBe("person.name is required to not be empty but it was")
    }

    data class Age(val value: String)

    class AgeValidator(label: String) : CompoundValidators<Age>(label) {
        val value = Age::value.validator().notBlank().required()
    }

    data class Student(val age: Age)

    class StudentValidator(label: String) : CompoundValidators<Student>(label) {
        val age = Student::age.validator { AgeValidator(it) }.required()
    }

    fun student(label: String = "student"): Validators<Student> = StudentValidator(label)

    @Test
    fun should_be_able_to_validate_deeply_nested_objects() {
        val validator = student().required()
        val res = expect(validator.validate(Student(Age("")))).toBe<Invalid<*>>()
        expect(res.reasons.first()).toBe("student.age.value is required to not be empty but it was")
    }

    fun person(label: String = "person") = PersonValidators(label)
}