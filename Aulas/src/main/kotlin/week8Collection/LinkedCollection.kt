package week8Collection

class LinkedCollection<E> : MutableCollection<E>{
    private class Node<E> (val value: E?){
        lateinit var prev: Node<E>
        lateinit var next: Node<E>
    }

    //<< Variáveis de instância >>
    private var count: Int = 0    // Número de elementos contidos.
    private val head: Node<E> = makeSentinel() // Nó sentinela.
    private var modCount = 0    // Para detetar modificações concorrentes quando da iteração

    override val size get() = count
    override fun isEmpty(): Boolean = count == 0
    override fun clear() {
        head.next = head
        head.prev = head
        count = 0
        ++modCount
    }

    private fun makeSentinel() : Node<E> {
        val n: Node<E> = Node<E>(null)
        n.prev = n
        n.next = n
        return n
    }

    private fun makeNode(suc: Node<E>, e:E): Node<E> {
        //return Node<E>(e).also { linkNode(it, suc) }
        val n = Node<E>(e)
        linkNode(n, suc)
        return n
    }

    private fun linkNode(n: Node<E>, suc: Node<E> ){
        val prev = suc.prev
        n.next = suc
        n.prev = prev
        prev.next = n
        suc.prev = n
    }

    private fun add(suc: Node<E>, e: E): Node<E> {
        ++count
        ++modCount
        return makeNode(suc, e);
    }

    fun addFirst(e: E) { add(head.next, e) }
    fun addLast(e: E)  { add(head, e)      }

    override fun add(element: E): Boolean {
        addLast( element )
        return true
    }

    override fun addAll(elements: Collection<E>): Boolean {
       elements.forEach { add(it) }
       return !elements.isEmpty()
    }

    private fun getNode(e: E): Node<E>? {
        var n = head.next
        while ( n != head) {
            if (n.value == e) return n
            n = n.next
        }
        return null
    }

    override fun contains(element: E)= getNode(element) != null
    override fun containsAll(elements: Collection<E>): Boolean {
        return elements.all { contains(it) }
        /*elements.forEach {
            if ( !contains(it)) return false
        }
        return false
        */
    }

    private fun unlinkNode(n: Node<E>) {
        n.prev.next = n.next
        n.next.prev = n.prev
    }

    private fun removeNode(n: Node<E>): Node<E> {
        --count
        ++modCount
        unlinkNode(n)
        return n
    }

    override fun remove(element: E): Boolean {
        return getNode(element)?.also{removeNode(it)} != null
 /*     val n = getNode( element)
        if ( n != null) {
            removeNode( n )
            return true
        }
        return false

  */
    }

    fun removeFirst(): E {
        check(!isEmpty())
        return removeNode(head.next).value as E
    }

    fun removeLast(): E {
        check(!isEmpty());
        return removeNode(head.prev).value as E
    }
    override fun retainAll(elements: Collection<E>): Boolean =
        removeIf{!elements.contains(it) }

    override fun removeAll(elements: Collection<E>): Boolean =
        removeIf{ elements.contains(it) }

    override fun iterator(): MutableIterator<E> =
        object: MutableIterator<E> {
            var expectedModCount = modCount
            var flagNext = false
            var last = head
            override fun hasNext(): Boolean = last.next!=head
            override fun next(): E {
                if (expectedModCount != modCount)
                    throw ConcurrentModificationException ()
                if (!hasNext()) throw NoSuchElementException()
                flagNext = true
                last = last.next
                return last.value as E
            }
            override fun remove() {
                if (expectedModCount != modCount)
                    throw ConcurrentModificationException ()
                check( flagNext )
                removeNode( last );
                last = last.prev;
                flagNext = false
                expectedModCount = modCount;
            }
        }

    private fun move(p: Node<E>, first:Node<E>, last:Node<E>) {
        last.prev.next = p
        first.prev.next = last
        p.prev.next = first
        val prev = p.prev
        p.prev = last.prev
        last.prev = first.prev
        first.prev = prev
    }

    fun merge( lst: LinkedCollection<E>,
               cmp: Comparator<E>) {
        require( lst !== this ){"the lists cannot be the same"}
        var first1 = head.next; val last1 = head
        var first2 = lst.head.next; val last2= lst.head
        while (first1 !== last1 && first2 !== last2) {
            if (cmp.compare(first1.value, first2.value) <= 0)
                first1= first1.next
            else {
                do { first2= first2.next
                } while ( first2!=last2 &&
                          cmp.compare(first2.value, first2.value)<0)
                move(first1, lst.head.next, first2)
            }
        }
        if (first2 !== last2)
            move(first1, first2, last2)
        count += lst.size
        lst.count = 0
    }

}