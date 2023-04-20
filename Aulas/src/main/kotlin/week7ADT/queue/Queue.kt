package week7ADT.queue

interface Queue<E> : Iterable<E>{
    val size: Int
    fun isEmpty(): Boolean
    fun peek(): E
    fun offer(e: E): Boolean
    fun poll() : E
}
