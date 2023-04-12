package week7ADT.queue

class ArrayQueue<T>(val capacity: Int) {
    private val array = arrayOfNulls<Any?> (capacity) as Array<T>
    private var count: Int = 0
    private var head: Int = 0
    private var tail: Int = 0

}

