package week7ADT.stack

/**
 * Created by msousa on 3/1/2019.
 */
class LinkedStack<T>  {
    private class Node<E>(val key: E, var next: Node<E>?)

    private var head: Node<T>? = null
}
