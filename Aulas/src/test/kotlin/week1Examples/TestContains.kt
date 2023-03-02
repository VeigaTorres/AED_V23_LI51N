package week1Examples

import kotlin.test.*

class TestContains {
    val a = Array(10){ it+1 }
    @Test
    fun test_first() {
        assertTrue(contains(a, 1))
    }
    @Test
    fun test_last() {
        assertTrue( contains(a, a.size))
    }
    @Test
    fun test_middle() {
        assertTrue( contains(a, a.size/2))
    }

    @Test
    fun test_not_exist() {
        assertFalse(contains(a, a.size + 1))
    }
}