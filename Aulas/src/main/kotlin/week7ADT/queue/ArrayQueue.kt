package week7ADT.queue

class ArrayQueue<E>(val capacity: Int) {
    private val array = arrayOfNulls<Any?> (capacity) as Array<E>
    private var count: Int = 0
    private var head: Int = 0
    private var tail: Int = 0

}

