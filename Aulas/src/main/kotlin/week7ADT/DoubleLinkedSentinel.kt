package week7ADT

class DoubleLinkedSentinel<E> : Iterable<E> {
    private class Node<E>  {
        val key: E
        var next: Node<E>
        var prev: Node<E>
        constructor( k: E = Any() as E )  {
            key = k
            next =this
            prev = this
        }
        constructor(v: E, p: Node<E>, n: Node<E>)  {
            key = v
            next = n
            prev = p
        }
    }

    private var sentinel= Node<E>()

    private fun listInsert( x: Node<E> ) {
        x.next = sentinel.next
        sentinel.next.prev = x
        sentinel.next = x
        x.prev= sentinel
    }

    fun add( k: E) {
        listInsert( Node(k) )
    }

    private fun listDelete( x: Node<E> ) {
        x.next.prev = x.prev
        x.prev.next = x.next
    }

    fun remove( k: E ) {
        val x = listSearch( k )
        if (x != sentinel) listDelete( x )
    }

    private fun listSearch( k: E ) : Node<E>{
        var x : Node<E> = sentinel.next
        while ( x != sentinel && x.key != k)
             x= x.next
        return x
    }

    fun contains( k: E ) : Boolean = listSearch(k) != sentinel

    override fun iterator(): Iterator<E> =
        object : Iterator<E> {
            private var curr = sentinel.next
            override fun hasNext(): Boolean = curr != sentinel
            override fun next(): E {
                if (!hasNext()) throw NoSuchElementException("stack empty")
                return curr.also { curr = it.next }.key as E
            }
        }

    override fun toString(): String =
        this.joinToString(prefix = "[", postfix = "]")
}