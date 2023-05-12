package week10HashTable

import week11Trees.AedTreeSet
import kotlin.test.*

class TestSet {
    private fun getSet(): MutableSet<Int> {
        return AedTreeSet(Comparator.naturalOrder())
        //return HashTableSet()
        //return HashSet()
    }

    private fun emptyTest(set: Set<Int>) {
        set.run {
            assertEquals(0, size)
            assertTrue(isEmpty())
            assertFalse(iterator().hasNext())
        }
    }

    private fun setEqualsTest(expected: Set<Int>, set: MutableSet<Int>) {
        assertEquals(expected.size, set.size)
        for (v in expected) {
            assertTrue(set.contains(v))
        }
        for (v in set) {
            assertTrue(expected.contains(v))
        }
    }

    @Test
    fun test_onInstantiation_EmptySet() {
        val set: Set<Int> = getSet()
        emptyTest(set)
    }

    @Test
    fun test_addOneElement() {
        getSet().run{
            add(6)
            assertEquals(1, size)
            assertTrue( contains( 6 ) )
            assertFalse(isEmpty())
            assertTrue(iterator().hasNext())
            iterator().run{
               assertEquals(6, next())
               assertFalse(hasNext())
            }
        }
    }

    @Test
    fun test_iteratorWithOneElement() {
        getSet().run{
            add(6)
            assertTrue(iterator().hasNext())
            iterator().run{
                assertEquals(6, next())
                assertFalse(hasNext())
            }
        }
    }

    @Test
    fun test_addAll() {
        val setTest = setOf(31, 20, 42, 10, 51, 63)
        getSet().run {
            addAll(setTest)
            setTest.forEach { assertFalse(add(it))}
            setEqualsTest(setTest, this)
        }
    }

    @Test
    fun test_remove() {
        val listTest: MutableList<Int> = ArrayList()
        for (i in 1..999) listTest.add(i)
        listTest.shuffle()
        val set = getSet()
        set.addAll(listTest)
        var size = set.size
        for (v in listTest) {
            set.remove(v)
            assertFalse(set.contains(v))
            assertEquals(--size, set.size)
        }
        emptyTest(set)
        set.addAll(listTest)
        assertTrue(set.containsAll(listTest))
    }

    @Test
    fun test_clear() {
        val setTest = setOf(3, 1, 2, 4, 6, 5)
        getSet().run{
            addAll(setTest)
            clear()
            emptyTest(this)
            addAll(setTest)
            setEqualsTest(setTest, this)
        }
    }

    @Test
    fun test_iterator_remove() {
        val listTest = listOf(16, 15, 7, 14, 13, 5, 6, 9, 10, 11, 12, 4, 3, 2, 1, 8)
        val listRemove = listOf(5, 8, 14, 13, 2, 7, 11, 9, 16)
        val set = getSet()
        set.addAll(listTest)
        val itRem  = listRemove.iterator()
        var v: Int? = itRem.next()
        while (v != null) {
            val size = set.size
            var i = 0
            var r = 0
            val it = set.iterator()
            while (it.hasNext()) {
                ++i
                if (it.next() == v) {
                    it.remove()
                    ++r
                    assertFalse(set.contains(v))
                    v = if (itRem.hasNext()) itRem.next() else null
                }
            }
            assertEquals(size, i)
            assertTrue(r != 0)
        }
        assertEquals( listTest.size - listRemove.size, set.size)
    }

    @Test
    fun test_removeAll() {
        val setTest = setOf(5, 2, 6, 4, 1, 3)
        getSet().run {
            addAll(setTest)
            removeAll(setTest)
            emptyTest(this)
            addAll(setTest)
            assertTrue(containsAll(setTest))
            setEqualsTest(setTest, this)
        }
    }

    @Test
    fun test_removeAllPartial() {
        val setTest = setOf(16, 15, 7, 14, 13, 5, 6, 9, 10, 11, 12, 4, 3, 2, 1, 8)
        val setRemove = setOf(5, 8, 14, 13, 2, 7, 11, 9, 16)
        val expected = setOf(15, 6, 10, 12, 4, 3, 1)
        getSet().run {
            addAll(setTest)
            assertTrue( removeAll(setRemove))
            assertEquals(expected.size, size)
            for( v in expected)
                assertTrue(contains( v ))
            assertFalse( removeAll(setRemove))
        }
    }

    @Test
    fun test_retainAll() {
        val listTest = listOf(16, 15, 7, 14, 13, 5, 6, 9, 10, 11, 12, 4, 3, 2, 1, 8)
        val listRetain = listOf(5, 8, 14, 13, 2, 25, 7, 11, 9, 30, 16, 40)
        val listExpected = listOf(5, 8, 14, 13, 2, 7, 11, 9, 16)
        getSet().run {
            addAll(listTest)
            assertTrue(retainAll(listRetain))
            assertEquals(listExpected.size, size)
            for( v in listExpected)
                assertTrue(contains( v ))
            assertFalse(retainAll(listExpected) )
        }
    }

    @Test
    fun test_nextWithoutElement() {
        val listTest = listOf(16)
        getSet().run {
            addAll(listTest)
            val iter: Iterator<Int> = iterator()
            iter.next()
            val exc = assertFailsWith<NoSuchElementException> { iter.next() }
            if (this !is HashSet)
                assertEquals("no more elements", exc.message)
        }
    }

    @Test
    fun test_removeFirstElementWithoutNext() {
        val listTest = listOf(16)
        getSet().run{
            addAll(listTest)
            val it = iterator()
            val exc = assertFailsWith<IllegalStateException> { it.remove() }
            if (this !is HashSet)
                assertEquals("not have next() before", exc.message)
        }
    }

    @Test
    fun test_removeTwoElementWithoutNext() {
        val listTest = listOf(16, 17, 18)
        getSet().run {
            addAll(listTest)
            iterator().run {
                next()
                remove()
                val exc = assertFailsWith<IllegalStateException> { remove() }
            }
        }
    }

    @Test
    fun test_getElementWithout_has_Next() {
        getSet().apply{add(16)}.iterator().run{
            assertTrue( hasNext() )
            next()
            assertFalse(hasNext())
            assertFailsWith<NoSuchElementException> { next() }
        }

    }
}