package week11Trees

import kotlin.test.*

class TestTree {
    private fun getSet(): AedTreeSet<Int> {
        return AedTreeSet(Comparator.naturalOrder())
    }

    @Test
    fun test_ifAllSorted() {
        val listTest = listOf(31, 20, 42, 10, 51, 63)
        getSet().run {
            addAll(listTest)
            assertTrue(containsAll(listTest))
            val listSorted = listTest.sorted()
            var i = 0
            for (v in this)
                assertEquals(listSorted[i++], v)
            assertEquals(i, size)
            assertEquals(listSorted.toString(), toString())
        }
    }

    @Test
    fun test_three_elements() {
        getSet().run {
            addAll(listOf(1, 2, 3))
            assertEquals(3, size)
            assertEquals(1, first())
            assertEquals(3, last())
            assertEquals(2, height())
            assertEquals(3, count())
            assertEquals("[1, 2, 3]", toString())

        }
    }

    @Test
    fun test_transverseBreadth() {
        getSet().run {
            addAll(listOf(1, 2, 3))
            val str = StringBuilder()
            transverseBreadthFirst { str.append("$it ") }
            assertEquals("1 2 3 ", str.toString())
            balance()
            assertEquals(3, size)
            assertEquals(1, first())
            assertEquals(3, last())
            assertEquals(1, height())
            assertEquals(3, count())
            assertEquals("[1, 2, 3]", toString())
            str.clear()
            transverseBreadthFirst { str.append("$it ") }
            assertEquals("2 1 3 ", str.toString())
        }
    }

    @Test
    fun test_balance() {
        getSet().run {
            addAll(listOf(1, 2, 3))
            balance()
            assertEquals(3, size)
            assertEquals(1, first())
            assertEquals(3, last())
            assertEquals(1, height())
            assertEquals(3, count())
            assertEquals("[1, 2, 3]", toString())
            val str = StringBuilder()
            transverseBreadthFirst { str.append("$it ") }
            assertEquals("2 1 3 ", str.toString())
        }
    }

    @Test
    fun test_seven_elements() {
        getSet().run {
            addAll(listOf(1, 2, 3, 4, 5, 6, 7))
            assertEquals(7, size)
            assertEquals(1, first())
            assertEquals(7, last())
            assertEquals(6, height())
            assertEquals(7, count())
            assertEquals("[1, 2, 3, 4, 5, 6, 7]", toString())
            assertFalse(isCompelete())
            val str = StringBuilder()
            transverseBreadthFirst { i -> str.append("$i ") }
            assertEquals("1 2 3 4 5 6 7 ", str.toString())
            balance()
            assertTrue(isCompelete())
            assertEquals(7, size)
            assertEquals(1, first())
            assertEquals(7, last())
            assertEquals(2, height())
            assertEquals("[1, 2, 3, 4, 5, 6, 7]", toString())
            str.clear()
            transverseBreadthFirst { str.append("$it ") }
            assertEquals("4 2 6 1 3 5 7 ", str.toString())
        }
    }

    @Test
    fun test_nesimo() {
        getSet().run {
            addAll(listOf(1, 2, 3, 4, 5, 6, 7))
            assertEquals(6, height())
            for (i in 1..7)
                assertEquals(i, nesimo(i))
            balance()
            assertEquals(2, height())
            for (i in 1..7)
                assertEquals(i, nesimo(i))
        }

    }

    @Test
    fun test_higher() {
        getSet().run {
            val res = listOf(1, 1, 3, 3, 5, 5, 7, 7)
            addAll(listOf(1, 3, 5, 7, 9, 12, 13))
            for (i in 1 .. 8)
                assertEquals(res[i-1], higher(i, Int::compareTo))
            balance()
            for (i in 1 .. 8)
                assertEquals(res[i-1], higher(i, Int::compareTo))
            assertNull(higher(0, Int::compareTo))
            assertEquals(13, higher(130, Int::compareTo))
        }
    }

    @Test
    fun test_findMinimumDepth() {
        getSet().run {
            val res = listOf(1, 1, 3, 3, 5, 5, 7, 7)
            addAll(listOf(1, 3, 5, 7, 9, 12, 13))
            assertEquals(6, height())
            assertEquals(6, findMinimumDepth())
            balance()
            assertEquals(2, height())
            assertEquals(2, findMinimumDepth())
            remove(1)
            assertEquals(2, height())
            assertEquals(2, findMinimumDepth())
            remove( 3)
            assertEquals(2, height())
            assertEquals(1, findMinimumDepth())
       }
    }
}