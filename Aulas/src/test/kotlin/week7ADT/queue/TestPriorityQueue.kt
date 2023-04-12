package week7ADT.queue

import kotlin.test.*

class TestPriorityQueue {
    val N = 10
    @Test
    fun testEmpty() {
        val pq = PriorityQueue<Int>(10, Int::compareTo)
        assertTrue( pq.isEmpty())
        assertEquals(0, pq.size)
    }

    @Test
    fun testOne() {
        val pq = PriorityQueue<Int>(10, Int::compareTo)
        pq.offer( 100 )
        assertFalse( pq.isEmpty())
        assertEquals(1, pq.size)
        assertEquals(100, pq.peek())
    }

    @Test
    fun testNotHeapify() {
        val values:Array<Int> = arrayOf(5, 30, 200, 1000)
        val pq = PriorityQueue<Int>(N, Int::compareTo)
        for ( v in values )
            pq.offer( v )
        assertFalse( pq.isEmpty())
        assertEquals(5, pq.peek())
        assertEquals(values.size, pq.size)

        for( v in values.sortedArray())
            assertEquals(v, pq.poll())
    }

    @Test
    fun testMax() {
        val values:Array<Int> = arrayOf(1000, 30, 200, 5)
        val pq = PriorityQueue<Int>(N) { a, b -> b.compareTo(a) }
        for ( v in values )
            pq.offer( v )
        assertFalse( pq.isEmpty())
        assertEquals(1000, pq.peek())
        assertEquals(values.size, pq.size)

        for( v in values.sortedArrayDescending() )
            assertEquals(v, pq.poll())
    }

    @Test
    fun testArrayCrescent() {
        val values:Array<Int> = Array(N) { it+1 }
        val pq = PriorityQueue<Int>(N, Int::compareTo)
        for ( v in values )
            pq.offer( v )
        for( v in 1 .. N )
            assertEquals(v, pq.poll())
        assertTrue(pq.isEmpty())
    }

    @Test
    fun testArrayDecrescent() {
        val N = 10
        val values:Array<Int> = Array(N) { N - it }
        val pq = PriorityQueue<Int>(N, Int::compareTo)
        for ( v in values )
            pq.offer( v )
        for( v in 1 .. N )
            assertEquals(v, pq.poll())
        assertTrue(pq.isEmpty())
    }
    @Test
    fun test_queue() {
        val q = PriorityQueue<Int>(N, Int::compareTo)
        assertTrue(q.isEmpty())
        assertEquals(0, q.size )
        for (i in 0 until N){
            assertTrue (q.offer( i ))
            assertEquals( i+1, q.size)
            assertEquals( 0, q.peek())
        }
        assertFalse(q.isEmpty())
        assertEquals( N, q.size)

        for (i in 0 until  N) {
            assertEquals(N-i, q.size)
            assertEquals(i , q.peek())
            assertEquals(i , q.poll())
        }
        assertTrue(q.isEmpty())
        assertEquals( 0, q.size)
    }
}