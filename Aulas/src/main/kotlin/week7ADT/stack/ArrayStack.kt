package week7ADT.stack

/**
 * Created by msousa on 3/1/2019.
 */
class ArrayStack<T>(val capacity: Int) {
    private val array: Array<T> = arrayOfNulls<Any>(capacity) as Array<T>
    private var top: Int = 0
}
