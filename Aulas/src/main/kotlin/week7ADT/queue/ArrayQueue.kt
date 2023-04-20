package week7ADT.queue

class ArrayQueue<E>(val capacity: Int) : Queue<E>{
    private val array = arrayOfNulls<Any?> (capacity) as Array<E>
    private var count: Int = 0
    private var head: Int = 0
    private var tail: Int = 0

    override val size
        get() = count

    override fun isEmpty(): Boolean = count == 0

    override fun peek(): E {
        if(isEmpty()) throw NoSuchElementException("queue empty")
        return array[head]
    }

    override fun poll(): E {
        check(!isEmpty()){ "queue empty"}
        val e = array[head]
        head = (head+1 )% array.size
        // ++head; if ( head == array.size) head= 0
        --count
        return e
    }

    override fun iterator(): Iterator<E> =
        object : Iterator<E> {
            var index= 0
            override fun hasNext(): Boolean = index < count

            override fun next(): E {
                val v = array[(index+head)% capacity]
                ++index
                return v
            }
        }

    override fun offer(e: E): Boolean {
        if ( count == capacity ) return false
        array[tail]= e
        tail = (tail+1) % array.size
        ++count
        return true
    }

    override fun toString(): String{
        return this.joinToString(",", "[", "]")

    }

}

