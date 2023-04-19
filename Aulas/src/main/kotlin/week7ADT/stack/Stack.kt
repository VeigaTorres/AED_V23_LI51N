package week7ADT.stack

interface Stack<E>: Iterable<E>{
    val size: Int
    fun isEmpty(): Boolean
    fun push(e: E)
    fun pop(): E
    fun peek(): E
}
