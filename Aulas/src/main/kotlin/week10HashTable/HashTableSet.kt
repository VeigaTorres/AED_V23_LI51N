package week10HashTable


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
        TODO()
    }

    override fun contains(key: K): Boolean {
        TODO()
    }


    override fun add(key: K) : Boolean {
        val hc = key.hashCode()
        val index = hash( hc )
        TODO()
    }

    override fun remove(key:K) : Boolean {
        val hc = key.hashCode()
        val index = hash( hc )
        TODO()
    }

    private fun expand() {
        TODO()
    }

    override fun clear() {
        TODO()
    }

    override fun iterator() = TODO()

    override fun containsAll(elements: Collection<K>): Boolean =
        elements.all{ contains(it) }

    override fun addAll(elements: Collection<K>): Boolean {
        val oldSize = count
        elements.forEach{ add(it) }
        return count != oldSize
    }
    override fun retainAll(elements: Collection<K>): Boolean
       = removeIf(){!elements.contains(it)}

    override fun removeAll(elements: Collection<K>): Boolean
       = removeIf { elements.contains( it) }

}
