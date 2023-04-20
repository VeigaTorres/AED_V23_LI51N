package week8Collection

import kotlin.test.*

class TestCollection {
    private fun emptyCollection():MutableCollection<Int> = TODO() //ArrayCollection<Int>()
    private fun emptyIterator() = emptyCollection().iterator()
    private fun addSequenceOf(start:Int, end: Int) :MutableCollection<Int>{
        /*
        val c= emptyCollection()
        for (i in 0 until end-start )
          c.add(i, start+i)
        return c
         */
        TODO()
    }
    @Test
    fun test_emptyCollection() {
        val c: Collection<Int> = emptyCollection()
        assertTrue(c.isEmpty())
        assertEquals(0, c.size)
        assertFalse(c.iterator().hasNext())
    }

    @Test
    fun test_emptyIterator() {
        val it: Iterator<Int> = emptyIterator()
        assertFalse(it.hasNext())
    }

    @Test
    fun test_singleton() {
        val c: Collection<Int> = addSequenceOf(2, 3)
        assertFalse(c.isEmpty())
        assertEquals(1, c.size)
        val it = c.iterator()
        assertTrue(it.hasNext())
        assertEquals(2, it.next())
        assertFalse(it.hasNext())
    }



    @Test
    fun test_add() {
        val c: Collection<Int> = addSequenceOf(1, 6)
        assertFalse(c.isEmpty())
        assertEquals(5, c.size)
        val it = c.iterator()
        for (i in c.indices) {
            assertEquals(5, c.size)
            assertTrue(it.hasNext())
            assertEquals(i+1, it.next())
        }
        assertFalse(it.hasNext())
    }


    @Test
    fun test_addAll() {
        val a = listOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        assertEquals(a.size, c.size)
        var expected = 0
        for (v in c) assertEquals(++expected, v)
        assertEquals(a.size, expected)
    }

    @Test
    fun test_sequence_two_iteration() {
        var vE = -3
        val c = addSequenceOf(vE, 15)
        assertEquals(c.size, 18)
        val it = c.iterator()
        while (vE < 14) {
            assertTrue(it.hasNext())
            assertEquals(vE++, it.next())
        }
        assertFalse(!it.hasNext())
        vE = -3
        for (v in c) {
            assertEquals(vE++, v)
        }
        assertEquals(15, vE)
    }

    @Test
    fun test_remove() {
        val c: MutableCollection<Int> = addSequenceOf(1, 6)
        assertEquals(5, c.size)
        val len = c.size
        for (i in 0 until len) {
            assertEquals(len-i, c.size)
            assertTrue(c.remove(i+1))
        }
        assertTrue(c.isEmpty())
    }

    @Test
    fun test_removeAll() {
        val a = arrayOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        assertEquals(a.size, c.size)
        assertFalse(c.removeAll(arrayOf(7, 8)))
        assertTrue (c.removeAll(addSequenceOf(1,4)))
        assertEquals(a.size-3, c.size)
        var ve = 4
        for ( v in c )
            assertEquals(ve++, v)
    }

    @Test
    fun test_retainAll() {
        val a = listOf(1, 2, 3, 4, 5, 6)
        val c: MutableCollection<Int> = emptyCollection()
        c.addAll( a )
        assertEquals(a.size, c.size)
        assertFalse(c.retainAll(a))
        assertTrue (c.retainAll(listOf(1, 3, 5, 10)))
        assertEquals(3, c.size)
        var ve = 1
        for ( v in c ) {
            assertEquals(ve, v)
            ve += 2
        }
    }

}