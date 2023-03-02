package week1Examples

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestPower {
    val power:(Int, Int)-> Int = ::power
    @Test
    fun testExpoent0() {
        assertEquals(1, power( 2, 0))
    }
    @Test
    fun testEven() {
        assertEquals(16, power(2, 4))
    }
    @Test
    fun testOdd() {
        assertEquals(32, power( 2, 5))
    }

    @Test
    fun test() {
        for( i in 1 .. 1000000)
            power( 3, 5000)
    }

}