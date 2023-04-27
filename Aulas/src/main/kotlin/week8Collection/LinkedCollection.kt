package week8Collection


class LinkedCollection<E> {  //MutableCollection<E>{
    private class Node<E> (val value: E?){
        lateinit var prev: Node<E>
        lateinit var next: Node<E>
    }

    //<< Variáveis de instância >>
    private var count: Int = 0    // Número de elementos contidos.
    private val head: Node<E> = makeSentinel()// Nó sentinela.

    val size get() = count

    fun isEmpty(): Boolean = count == 0

    private fun makeSentinel() : Node<E> {
        val n: Node<E> = Node<E>(null)
        n.prev = n
        n.next = n
        return n
    }

    private fun makeNode(suc: Node<E>, e:E): Node<E> {
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
        ++count; return makeNode(suc, e);
    }

    fun addFirst(e: E) { add(head.next, e) }
    fun addLast(e: E)  { add(head, e)      }

    private fun unlinkNode(n: Node<E>) {
        n.prev.next = n.next
        n.next.prev = n.prev
    }

    private fun removeNode(n: Node<E>): Node<E> {
        unlinkNode(n)
        --count
        return n
    }

    fun removeFirst(): E {
        check(!isEmpty())
        return removeNode(head.next).value as E
    }

    fun removeLast(): E {
        check(!isEmpty());
        return removeNode(head.prev).value as E
    }

    private fun getNode(e: E): Node<E>? {
        var n = head.next
        while ( n != head)
            if  (n.value == e ) return n
            else n = n.next
        return null
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