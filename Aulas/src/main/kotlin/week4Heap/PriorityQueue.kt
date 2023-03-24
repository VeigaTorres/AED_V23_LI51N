package week4Heap

/*
 * A PriorityQueue é implementada com um MinHeap
 */
class PriorityQueue<T> (capacity:Int, val comparator:(T, T)-> Int)  {
    private val heap:Array<T> = arrayOfNulls<Any>(capacity) as Array<T>
    private var heapSize= 0

    fun size():Int  = heapSize
    
    fun isEmpty(): Boolean = size() == 0

    fun peek(): T { // Complexidade O(1)
        check( !isEmpty() ){"illegal operation (peek): empty heap"}
        return heap[0]
    }
    fun poll( ): T { // Complexidade O(lg n)
        check( !isEmpty() ){"illegal operation (poll): empty heap"}
        return extractMaxHeap(heap, heapSize--, comparator)
    }
    fun offer( v: T):Boolean { // Complexidade O (lg n)
        if ( heapSize >= heap.size) return false
        heapIncreaseKey(heap, heapSize, v,  comparator)
        ++heapSize
        return true
    }
}