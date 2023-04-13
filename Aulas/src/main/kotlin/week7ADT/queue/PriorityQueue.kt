package week7ADT.queue

import week4Heap.extractMaxHeap
import week4Heap.heapIncreaseKey
import week4Heap.maxHeapify
import kotlin.Comparator

class PriorityQueue<E> (val capacity: Int, val comparator: Comparator<E>) : Queue<E>, Iterable<E>{
    private val compare: (E,E)->Int = { a, b-> comparator.compare(b, a)}
    private val heap: Array<E> = arrayOfNulls<Any>(capacity) as Array<E>
    private var sizeHeap: Int = 0
    override fun isEmpty(): Boolean = sizeHeap == 0
    override val size: Int get() =  sizeHeap
    override fun offer(v: E): Boolean { // O(lg n)
        if ( sizeHeap >= heap.size ) return false
        heap[sizeHeap] = v
        heapIncreaseKey(heap , sizeHeap++, v, compare)
        return true
    }

    override fun poll(): E { // O(lg n)
        check( !isEmpty() ) {"priority queue empty"}
        return extractMaxHeap(heap, sizeHeap--, compare)
    }

    override fun peek(): E { // O(1)
        if( isEmpty() ) throw NoSuchElementException("priority queue empty")
        return heap[0]
    }

    operator fun contains(v: E): Boolean { // O (n)
        TODO()
    }

    fun remove(v: E): Boolean { // O (n) + O(lg n) = O(n)
         TODO()
    }

    fun replace(i: Int, v: E) {  // O(lg n)
        TODO()
    }

    override fun iterator(): Iterator<E> {
        return object : Iterator<E> {
            private var i = 0
            override fun hasNext() = i < size
            override fun next(): E {
                if ( !hasNext() ) throw NoSuchElementException("no more elements")
                return heap[i++]
            }
        }
    }
}