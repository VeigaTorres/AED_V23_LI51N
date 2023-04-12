package week7ADT.stack

/**
 * Created by msousa on 3/1/2019.
 */
interface Stack<E>  {
    val size: Int
    fun isEmpty(): Boolean
    fun push(e: E)
    fun pop(): E
    fun peek(): E
}
