package week1Examples

import kotlin.test.*

class TestMax {
    @Test
    fun test_first() {
        assertEquals(10, max(Array(10){ 10 - it }))
    }

    @Test
    fun test_last() {
        assertEquals(10, max(Array(10){ it+1 }))
    }

    @Test
    fun test_middle() {
        assertEquals(6, max(arrayOf(1, 2, 3, 6, 2, 4 )))
    }

    @Test
    fun test_not_exist() {
        val e= assertFailsWith<IllegalArgumentException>{ max(arrayOf())}
        assertEquals("Array empty", e.message)
    }
}