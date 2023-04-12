package week7ADT.queue

import kotlin.test.*


class TestQueue {
    val N = 10
    fun getQueue(n: Int): Queue<Int> {
       TODO()
    }
    @Test
    fun testEmpty() {
        val q = getQueue(N)
        assertTrue( q.isEmpty())
        assertEquals(0, q.size)
    }

    @Test
    fun testOne() {
        val q = getQueue(N)
        assertTrue (q.offer( 100 ))
        assertFalse( q.isEmpty())
        assertEquals(1, q.size)
        assertEquals(100, q.peek())
    }

    @Test
    fun testWithArray() {
        val values:Array<Int> = arrayOf(5, 30, 200, 1000)
        val q = getQueue(values.size)
        for ( v in values )
            assertTrue(q.offer( v ))
        assertFalse(q.offer(4))
        assertFalse(q.isEmpty())
        assertEquals(values.first(), q.peek())
        assertEquals(values.size, q.size)

        for( v in values)
            assertEquals(v, q.poll())

        assertTrue(q.isEmpty())
        assertEquals(0, q.size)

    }

    @Test
    fun testQueue() {
        val q =getQueue(N)
        assertTrue(q.isEmpty())
        assertEquals(0, q.size )
        for (i in 0 until N){
            assertTrue(q.offer( i ))
            assertEquals( i+1, q.size)
            assertEquals( 0, q.peek())
        }
        assertFalse(q.isEmpty())
        assertEquals( N, q.size)

        for (i in 0 until N) {
            assertEquals(N-i, q.size)
            assertEquals(i , q.peek())
            assertEquals(i, q.poll())
        }
        assertTrue(q.isEmpty())
        assertEquals( 0, q.size)
    }
}