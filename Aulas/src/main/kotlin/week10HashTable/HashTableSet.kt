package week10HashTable

import java.lang.IllegalStateException


class HashTableSet<K> (capacityInitial:Int=5,
                       val loadFactor: Float= 0.75f): MutableSet<K> {
    private class Node<K> (  val key: K,
                             var next: Node<K>?,
                             val hc: Int )
    private var table: Array< Node<K>? > = arrayOfNulls(capacityInitial)
    private var count = 0
    private var threshold =  (capacityInitial * loadFactor).toInt()

    val capacity: Int get() = table.size
    override val size: Int get() = count

    private fun hash( hc: Int ): Int = hc.and( 0x7fff_ffff ) % capacity

    override fun isEmpty(): Boolean = size == 0

    private fun getNode(key: K, hc:Int, index: Int ): Node<K>? {
        var curr = table[index]
        while( curr != null ) {
            if ( hc == curr.hc && curr.key == key)
                return curr
            curr = curr.next
        }
        return null
    }

    override fun contains(element: K): Boolean {
        val hc = element.hashCode()
        val index = hash( hc )
        return getNode(element, hc, index) != null
    }


    override fun add(element: K) : Boolean {
        val hc = element.hashCode()
        val index = hash( hc )
        if ( getNode( element, hc, index) != null ) return false
        val node = Node<K>( element, table[index], hc)
        table[index] = node
        ++count
        if( count > threshold  ) expand()
        return true
    }

    override fun remove(element:K) : Boolean { //O(1)
        val hc = element.hashCode()
        val index = hash( hc )
        var prev: Node<K>? = null
        var curr = table[index]
        while ( curr != null ) {
            if ( curr.hc == hc &&  curr.key == element ) {
                if ( prev != null )
                    prev.next = curr.next
                else
                    table[index]= curr.next
                --count
                return true
            }
            prev = curr
            curr= curr.next

        }
        return false
    }

    private fun expand() {
       val oldTable = table
       table = arrayOfNulls(capacity * 2 )
       threshold = (capacity * loadFactor).toInt()
       for( oldIndex in oldTable.indices) {
           var node = oldTable[oldIndex]
           while ( node != null ) {
               oldTable[oldIndex] = node.next;
               val newIndex = hash(node.hc)
               node.next = table[newIndex]
               table[newIndex] = node
               node = oldTable[oldIndex]
           }
       }
    }

    override fun clear() {
        table.fill(null)
        count=0
    }

    override fun iterator() =
        object : MutableIterator<K> {
            var index = -1
            var curr = advance()
            var prev: Node<K>? = null

            fun advance(): Node<K>? {
                while( ++index < capacity && table[index] == null ) {}
                if ( index < capacity ) return table[index]
                return null
            }
            override fun hasNext(): Boolean = curr != null

            override fun next(): K {
               val n = curr?: throw NoSuchElementException("no more elements")
               curr = n.next
               prev = n
               if ( curr == null ) curr = advance()
               return n.key
            }

            override fun remove() {
                val n = prev?: throw IllegalStateException("not have next() before")
                remove( n.key )
                prev = null
            }

        }

    override fun containsAll(elements: Collection<K>): Boolean =
        elements.all{ contains(it) }

    override fun addAll(elements: Collection<K>): Boolean {
        val oldSize = count
        elements.forEach{ add(it) }
        return count != oldSize
    }
    override fun retainAll(elements: Collection<K>): Boolean =
        removeIf { !elements.contains(it) }

    override fun removeAll(elements: Collection<K>): Boolean {
        var result = false
        elements.forEach { if(remove(it)) result= true }
        return result
    }

}
