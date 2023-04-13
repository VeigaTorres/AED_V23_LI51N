package week7ADT.stack

class LinkedStack<E>() : Stack<E>{
    private class Node<E>(val key: E, var next: Node<E>?)

    private var head: Node<E>? = null
    private  var count = 0;
    override val size: Int
        get() = count // O(1)
    /*{ // O(n)
            var count= 0
            var curr = head
            while ( curr != null ) {
                ++count
                curr= curr.next
            }
            return count
        }
    */
    override fun isEmpty(): Boolean = head== null

    override fun pop(): E {
        val first = checkNotNull( head){""}
        head = first.next
        --count
        return first.key
    }

    override fun peek(): E = (head?:throw  NoSuchElementException("")).key

    override fun push(e: E) {
        ++count
        val n = Node( e, head)
        head = n
        // head = Node(e, head)
    }

}
