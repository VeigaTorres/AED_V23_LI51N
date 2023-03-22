package week4Heap

/*
 * A PriorityQueue é implementada com um MinHeap
 */
class PriorityQueue<T> (capacity:Int, val comparator:(T, T)-> Int)  {
    private val heap:Array<T> = arrayOfNulls<Any>(capacity) as Array<T>
    private var heapSize= 0

    val size get():Int  = TODO()
    
    fun isEmpty(): Boolean = TODO()

    fun peek(): T {
        check( !isEmpty() ){"illegal operation (peek): empty heap"}
        TODO()
    }
    fun poll( ): T {
        check( !isEmpty() ){"illegal operation (poll): empty heap"}
        TODO()
    }
    fun offer( v: T):Boolean {
        TODO()
    }
}