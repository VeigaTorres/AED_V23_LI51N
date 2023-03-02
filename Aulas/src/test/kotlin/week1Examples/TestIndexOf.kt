package week1Examples

import kotlin.test.*

class TestIndexOf {
    val a = Array(10){ it+1 }

    @Test
    fun test_first() {
        assertEquals(0, indexOf(a, 1))
    }
    @Test
    fun test_last() {
       assertEquals(a.size-1, indexOf(a, a.size))
    }
    @Test
    fun test_middle() {
       assertEquals(a.size/2-1, indexOf(a, a.size/2))
    }

    @Test
    fun test_not_exist() {
        assertEquals(-1, indexOf(a, a.size+1))
    }
}