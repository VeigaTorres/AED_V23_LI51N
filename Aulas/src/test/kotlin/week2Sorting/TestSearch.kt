package week2Sorting

import kotlin.test.*

class TestSearch  {
    val N = 1000
    val a = Array<Int>(N){it}

     private fun searchAll( a: Array<Int>,
                            search: (Array<Int>, Int, Int, Int)-> Boolean) {
        for (i in a.indices)
            assertTrue( search( a, a[i], 0, a.size-1),"the array not contain ${a[i]}")
        assertFalse(  search(a, a.size ) )
    }

    @Test
    fun testSearch() {
        a.shuffle()
        searchAll(a, ::search)
    }

    @Test
    fun testSearchRecursive() {
        a.shuffle()
        searchAll(a, ::searchRecursive)
    }

    @Test
    fun testAllSearchBinarySearch() {
        a.sort();
        searchAll(a, ::searchBinary)
    }

    @Test
    fun testSearchBinaryRecursive() {
        a.sort()
        searchAll(a, ::searchBinaryRecursive)
    }


    @Test
    fun testLowerBound() {
        // empty array
        val empty: Array<Int> = emptyArray();
        assertEquals(0, lowerBound( empty,0))

        // all equals
        val a: Array<Int> = Array(10){4};
        assertEquals(0, lowerBound(a,3))
        assertEquals(0, lowerBound(a,4))
        assertEquals(10, lowerBound( a,5))
        a[0] = 3
        assertEquals(1, lowerBound(a,4))
        a[9] = 5
        assertEquals(9, lowerBound( a,5))

        // return greater
        val b= arrayOf(1, 3, 5)
        assertEquals(0, lowerBound(b, -1))
        assertEquals(3, lowerBound(b, 6))
        assertEquals(1, lowerBound(b, 2))
        assertEquals(2, lowerBound(b, 4, 0, 2))
        assertEquals(2, lowerBound(b, 5, 0, 1))
     }

}
