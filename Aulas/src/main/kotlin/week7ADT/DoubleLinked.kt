package week7ADT

class DoubleLinked<E> : Iterable<E> {
    private class Node<E> (val key:E) {
        var next: Node<E>? = null
        var prev: Node<E>? = null
    }

    private var head: Node<E>? = null

    private fun listInsert( x: Node<E> ) {
        x.next = head
        x.prev = null
        head?.prev = x
        head = x
    }
    fun add( k: E) {
        listInsert( Node(k) )
    }

    private fun listDelete( x: Node<E> ) {
        val prev= x.prev
        if ( prev != null ) prev.next = x.next
        else head= x.next

        val next= x.next
        if ( next != null ) next.prev= x.prev
        // x.next?.prev = x.prev
    }

    fun remove( k: E ) {
        val x = listSearch( k )
        if ( x != null) listDelete( x )
        // listSearch( k )?.let {listDelete( it ) }
    }

    private fun listSearch( k: E ) : Node<E>? {
        var x: Node<E>? = head
        while (x != null && x.key != k)
             x = x.next
        return x
    }

    fun contains( k: E ): Boolean = listSearch( k ) != null

    override fun iterator(): Iterator<E> =
        object : Iterator<E> {
            private var curr = head
            override fun hasNext(): Boolean =curr != null
            override fun next(): E =
                (curr?: throw NoSuchElementException("stack empty")).also{
                    curr= it.next
                }.key
        }

    override fun toString(): String =
        this.joinToString(prefix = "[", postfix = "]")

}