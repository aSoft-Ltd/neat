package neat

import kommander.expect
import kommander.toBe
import kotlin.reflect.KMutableProperty0
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class StringValidationTest {

    @Test
    fun should_be_able_to_create_a_void_string_validator() {
        val validator = string("test").required()
        expect(validator.validate("test")).toBe<Valid<*>>()
    }

    @Test
    fun should_be_able_to_create_a_string_validator_that_can_validate_an_optional_string() {
        val validator = string("test").optional()
        expect(validator.validate(null)).toBe<Valid<*>>()
    }

    @Test
    fun should_be_able_to_mark_a_string_with_a_length_of_4_as_valid() {
        val validator = string().length(4).required()
        expect(validator.validate("test")).toBe<Valid<*>>()
    }

    @Test
    fun should_be_able_to_mark_a_string_with_a_length_greater_than_4_as_invalid() {
        val validator = string("test").length(4).required()
        val res = expect(validator.validate("testimony")).toBe<Invalid<*>>()
        expect(res.reasons.first()).toBe("test should have 4 character(s), but testimony has 9 character(s) instead")
    }

    @Test
    fun should_be_able_to_mark_an_optional_string_with_a_length_of_4_as_valid() {
        val validator = string().length(4).optional()
        expect(validator.validate(null)).toBe<Valid<*>>()
    }

    @Test
    fun should_return_all_reasons_for_invalid_values() {
        val validator = string().length(4).max(5).optional()
        val result = expect(validator.validate("testimony")).toBe<Invalid<*>>()
        val reason1 = result.reasons[0]
        expect(reason1).toBe("unnamed should have 4 character(s), but testimony has 9 character(s) instead")
        val reason2 = result.reasons[1]
        expect(reason2).toBe("unnamed should have less than 5 character(s), but testimony has 9 character(s) instead")
    }

    @Test
    fun should_be_able_to_mark_an_optional_string_with_a_length_of_4_as_valid_when_declaration_order_is_switched() {
        val validator = string().length(4).optional()
        expect(validator.validate(null)).toBe<Valid<*>>()
    }

    @Test
    fun should_be_able_to_mark_an_optional_string_with_a_length_greater_than_4_as_invalid() {
        val validator = string("test").max(4).optional()
        val res = expect(validator.validate("testimony")).toBe<Invalid<*>>()
        expect(res.reasons.first()).toBe("test should have less than 4 character(s), but testimony has 9 character(s) instead")
    }

    @Test
    fun should_fail_if_a_required_string_is_blank() {
        val validator = string("test").notBlank().required()
        val res = expect(validator.validate("")).toBe<Invalid<*>>()
        expect(res.reasons.first()).toBe("test is required to not be empty but it was")
    }

    @Test
    fun should_fail_to_retrieve_information_that_are_not_set_from_validators() {
        val validator = string("test").notBlank().optional()
        expect(validator.length).toBe(null)
    }

    @Test
    fun should_fail_to_retrieve_information_that_are_set_from_validators() {
        val validator = string("test").length(4).required()
        expect(validator.length).toBe(4)
    }

    // Commented out coz wasm was failing on kotlin 2.0.0-beta2. Before removing this code block. Just try to see if the current kotlin compiler can run this test
//    var name: String? = "test"
//    @Test
//    fun should_be_able_to_chain_optional_validators_with_a_block_as_if_it_is_not_optional() {
//        val validator = string(::name).length(4).optional()
//        expect(validator.length).toBe(4)
//    }
}