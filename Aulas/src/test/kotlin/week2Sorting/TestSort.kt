package week2Sorting

import week4Heap.heapSort
import week5QuickSort.quickSortHybrid
import week6LinearSort.countingSort
import week6LinearSort.radixSort
import kotlin.random.Random
import kotlin.test.*

 class TestSort  {
    val N = 1000

    fun sortArray( a: Array<Int>, l: Int, r: Int ) =
        //insertionSort(a,l, r, Int::compareTo)
        //bubbleSortFlag(a, l, r)
        //selectionSort(a, l, r)
        //mergeSort(a, l, r)
        //heapSort(a, Int::compareTo)
        //quickSortHybrid(a,0, a.size-1, Int::compareTo)
        //countingSort(a)
        radixSort( a )
    fun testSorted( a: Array<Int> ) {
        val expect = a.sortedArray()
        sortArray(a, 0, a.size-1)
        assertTrue(expect.contentEquals(a), "array not sorted")
    }

    @Test
    fun testSortEmptyArray() = testSorted(emptyArray())

    @Test
    fun testSortOneElement() = testSorted(arrayOf(0))

    @Test
    fun testSortTwoElement() {
        testSorted(arrayOf(2, 2))
        testSorted(arrayOf(2, 1))
        testSorted(arrayOf(1, 2))
    }

    @Test
    fun testSortThreeElement() {
        testSorted(arrayOf(1, 3, 2))
        testSorted(arrayOf(2, 1, 3))
        testSorted(arrayOf(3, 2, 1))
        testSorted(arrayOf(1, 2, 3))
    }

    @Test
    fun testSortRandomArray() = testSorted(Array(N) { Random.nextInt(N) })

    @Test
    fun testSortIncreasingArray() = testSorted(Array(N){it} )

    @Test
    fun testSortDecreasingArray() = testSorted(Array(N){N-it})

    @Test
    fun testAllValuesEquals() = testSorted(Array(N, {0}))
}
