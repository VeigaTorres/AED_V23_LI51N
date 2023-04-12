package week7ADT.queue

interface Queue<T> {
    val size: Int
    fun isEmpty(): Boolean
    fun peek(): T
    fun offer(e: T): Boolean
    fun poll() : T
}
