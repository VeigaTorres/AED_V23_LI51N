package week7ADT.stack

class ArrayStack<E>(val capacity: Int) : Stack<E> {
    private val array: Array<E> = arrayOfNulls<Any>(capacity) as Array<E>
    private var top: Int = 0

    override val size: Int get() = top
    override fun isEmpty(): Boolean= top == 0
    override fun push(e: E) {
        check( top < capacity){ "underflow"}
        array[top++]= e
    }

    override fun pop() : E {
        check(!isEmpty()){"stack empty"}
        return array[--top]
    }

    override fun peek(): E {
        if( isEmpty() ) throw NoSuchElementException("stack empty")
        return array[top -1]
    }

    private inner class Iter : Iterator<E> {
        var index = size // Para que seja iterado pela ordem do pop
        override fun hasNext(): Boolean = index > 0

        override fun next(): E {
            if( !hasNext() ) throw NoSuchElementException( "no more elements")
            return array[--index]
        }
    }

    override fun iterator(): Iterator<E> = Iter()

    override fun toString(): String{
        return this.joinToString(",", "[", "]")

    }
}
