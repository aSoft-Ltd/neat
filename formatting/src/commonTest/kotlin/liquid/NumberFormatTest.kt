package liquid

import kommander.expect
import kotlin.test.Test

class NumberFormatTest {
    @Test
    fun should_easily_abbreviate_hundreds_of_thousands() {
        expect(450_000.format(abbreviate = true)).toBe("450K")
    }

    @Test
    fun should_easily_abbreviate_thousands() {
        expect(3000.format(abbreviate = true)).toBe("3K")
        expect(5000.format(abbreviate = true)).toBe("5K")
        expect(4200.format(abbreviate = true)).toBe("4.2K")
        expect(4231.format(abbreviate = true, decimals = 1)).toBe("4.2K")
        expect(4231.format(abbreviate = true, decimals = 0)).toBe("4K")
        expect(4231.format(abbreviate = true)).toBe("4.2K")
    }

    @Test
    fun should_easily_millions() {
        expect(2_000_000.format(abbreviate = true)).toBe("2M")
    }

    @Test
    fun should_easily_abbreviate_decimals_too() {
        expect(2_160_000.format(abbreviate = true)).toBe("2.2M")
        expect(2_160_000.format(abbreviate = true, decimals = 2)).toBe("2.16M")
    }

    @Test
    fun should_easily_display_commas_in_thousands() {
        expect(4500.format()).toBe("4,500")
    }

    @Test
    fun should_easily_display_commas_with_decimals_too() {
        expect(6700.05.format(abbreviate = false)).toBe("6,700.05")
        expect(6700.09.format(decimals = 1)).toBe("6,700.1")
        expect(6700.06.format(decimals = 1)).toBe("6,700.1")
    }

    @Test
    fun should_easily_display_commas_with_forced_decimals_too() {
        expect(6700.format(decimals = 3, enforceDecimals = true)).toBe("6,700.000")
        expect(6700.05.format(abbreviate = false, enforceDecimals = true)).toBe("6,700.05")
        expect(6700.09.format(decimals = 3, enforceDecimals = true)).toBe("6,700.090")
        expect(6700.06.format(decimals = 2, enforceDecimals = true)).toBe("6,700.06")
    }
}