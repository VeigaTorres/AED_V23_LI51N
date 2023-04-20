package week7ADT.queue

import week4Heap.extractMaxHeap
import week4Heap.heapIncreaseKey
import week4Heap.maxHeapify
import kotlin.Comparator

class PriorityQueue<E> (val capacity: Int, val comparator: Comparator<E>) : Queue<E>{
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
        for( value in  this )
            if ( value == v ) return true
        return false
    }

    fun remove(v: E): Boolean { // O (n) + O(lg n) = O(n)
         for ( i in 0 until size )
             if ( heap[ i ] == v ) {
                 replace( i, heap[--sizeHeap])
/*
                 heap[i] = heap[--sizeHeap]
                 if ( compare(heap[i], v )< 0 )
                    maxHeapify(heap, sizeHeap, i, compare)
                 else
                     heapIncreaseKey(heap, i, heap[i], compare)
*/
                 return true
             }
        return false
    }

    fun replace(i: Int, v: E) {  // O(lg n)
        val old= heap[i]
        heap[i] = v
        if (compare(v, old) >0)
            heapIncreaseKey(heap, i, v, compare  )
        else
            maxHeapify(heap, sizeHeap, i, compare)
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

    override fun toString(): String{
        return this.joinToString(",", "[", "]")

    }

}