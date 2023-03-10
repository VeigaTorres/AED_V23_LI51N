package week1Examples

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestMaximumSubArray {
    val maxSubArray:(Array<Double>, Int, Int)-> Triple<Int, Int, Double> = ::maximumSubArray
    @Test
    fun testAllNegatives() {
        val a: Array<Double> = arrayOf(-2.0, -5.0, -1.0)
        val b = maxSubArray(a, 0, a.size-1)
        assertEquals( Triple(2, 2, -1.0), b)
    }
    @Test
    fun testAllPositive() {
        val a: Array<Double> = arrayOf(2.0, 5.0, 1.0)
        val b = maxSubArray(a, 0, a.size - 1)
        assertEquals(Triple(0, 2, 8.0), b)
    }
    @Test
    fun testMix() {
        val a: Array<Double> = arrayOf(2.0, -3.0, 5.0, -2.0, 3.0)
        val b = maxSubArray(a, 0, a.size - 1)
        assertEquals(Triple(2, 4, 6.0), b)
    }

    //@Test
    fun test1() {
        val a =Array(10000){ -5.0 }
        maximumSubArrayQuad( a , 0, a.size-1)
    }

    //@Test
    fun test2() {
        val a =Array(1000000){ -5.0 }
        maximumSubArray( a , 0, a.size-1)
    }

}