package week7ADT.queue

import week4Heap.buildMaxHeap
import week4Heap.extractMaxHeap
import week4Heap.heapIncreaseKey
import week4Heap.maxHeapify
import kotlin.Comparator

class PriorityQueue<T> (val capacity: Int, val comparator: Comparator<T>) : Queue<T>, Iterable<T>{
    private val compare: (T,T)->Int = { a, b-> comparator.compare(b, a)}
    private val heap: Array<T> = arrayOfNulls<Any>(capacity) as Array<T>
    private var sizeHeap: Int = 0
    override fun isEmpty(): Boolean = sizeHeap == 0
    override val size: Int get() =  sizeHeap
    override fun offer(v: T): Boolean { // O(lg n)
        if ( sizeHeap >= heap.size ) return false
        heap[sizeHeap] = v
        heapIncreaseKey(heap , sizeHeap++, v, compare)
        return true
    }

    override fun poll(): T { // O(lg n)
        check( !isEmpty() ) {"priority queue empty"}
        return extractMaxHeap(heap, sizeHeap--, compare)
    }

    override fun peek(): T { // O(1)
        if( isEmpty() ) throw NoSuchElementException("priority queue empty")
        return heap[0]
    }

    fun replace(i: Int, v: T) {  // O(lg n)
        TODO()
    }

    operator fun contains(v: T): Boolean { // O (n)
        TODO()
    }

    fun remove(v: T): Boolean { // O (n) + O(lg n) = O(n)
         TODO()
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            private var i = 0
            override fun hasNext() = i < size
            override fun next(): T {
                if ( i >= size ) throw NoSuchElementException("no more elements")
                return heap[i++]
            }
        }
    }
}