package week7ADT.queue

class LinkedQueue<T>  {
    private class Node<T> (val key: T, var next: Node<T>? = null)

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

}
