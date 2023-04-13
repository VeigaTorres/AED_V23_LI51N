package week7ADT.queue

class LinkedQueue<E> : Queue<E> {
    private class Node<T> (val key: T, var next: Node<T>? = null)

    private var head: Node<E>? = null
    private var tail: Node<E>? = null
    private var count = 0
    override val size: Int get() = count

    override fun isEmpty(): Boolean = count == 0

    override fun peek(): E =
        (head?:throw NoSuchElementException("queue empty")).key

    override fun poll(): E {
        val first = checkNotNull( head )
        head = first.next
        if ( head == null ) tail = null
        --count
        return first.key
    }

    override fun offer(e: E): Boolean {
        val n = Node(e, null)
        val last = tail
        if( last == null) head = n
        else last.next = n
        tail = n
        ++count
        return true;
    }

}
