package week7ADT.stack

import kotlin.test.*


class TestStack {
    val N = 10

    fun getStack(n: Int): Stack<Int> {
        TODO()
    }
    @Test
    fun testEmpty() {
        val st = getStack(N)
        assertTrue( st.isEmpty())
        assertEquals(0, st.size)
    }

    @Test
    fun testOne() {
        val st = getStack(1)
        st.push( 100 )
        assertFalse( st.isEmpty())
        assertEquals(1, st.size)
        assertEquals(100, st.peek())
    }

    @Test
    fun testWithArray() {
        val values:Array<Int> = arrayOf(5, 30, 200, 1000)
        val st = getStack(values.size)
        for ( v in values )
            st.push( v )
        assertFalse( st.isEmpty())
        assertEquals(values.last(), st.peek())
        assertEquals(values.size, st.size)

        for( i in values.size-1 downTo  0)
            assertEquals(values[i], st.pop())

        assertTrue( st.isEmpty())
        assertEquals(0, st.size)
    }

    @Test
    fun testStack() {
        val q =getStack(N)
        assertTrue(q.isEmpty())
        assertEquals(0, q.size )
        for (i in 0 until N){
            q.push( i )
            assertEquals( i+1, q.size)
            assertEquals( i, q.peek())
        }
        assertFalse(q.isEmpty())
        assertEquals( N, q.size)

        for (i in N downTo   1) {
            assertEquals(i, q.size)
            assertEquals(i-1 , q.peek())
            assertEquals(i-1, q.pop())
        }
        assertTrue(q.isEmpty())
        assertEquals( 0, q.size)
    }
}