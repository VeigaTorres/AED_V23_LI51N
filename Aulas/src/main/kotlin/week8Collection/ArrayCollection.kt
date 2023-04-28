package week8Collection

public class ArrayCollection<E>( capacityInicial: Int = 10 ):  RandomAccess, MutableCollection<E>{
    //<< Variáveis de instância >>
    private var count = 0       // Número de elementos contidos.
    private var array: Array<E> = allocate( capacityInicial )// Bloco de memória alojado.
    private var modCount = 0    // Para detetar modificações concorrentes quando da iteração
    val capacity: Int get() = array.size
    override val size: Int get() = count

    operator fun get(index: Int): E {
        require( index < size)
        return array[ index]
    }

    operator fun set(index: Int, element: E): E {
        require( index < size)
        array[ index] = element
        return array[ index]
    }
    override fun isEmpty() = size == 0
    @Suppress("UNCHECKED_CAST")
    private fun  allocate( n: Int ): Array<E> =
        arrayOfNulls<Any>(n) as Array<E>

    fun add( i: Int, e: E) {
        require( i <= size )
        val aux = array;
        if ( size == capacity ) {
            array = allocate( capacity * 2 )
            aux.copyInto(array, 0, 0, i)
        }
        aux.copyInto(array,i+1, i, size)
        array[i] = e
        ++count
        ++modCount
    }

    override fun add( element: E ): Boolean{
        add(size, element)
        return true
    }

    override fun addAll(elements: Collection<E>): Boolean {
        elements.forEach { add( it )}
        return !elements.isEmpty()
    }

    fun removeAt(index: Int) {
        require(index < size)
        array.copyInto(array, index, index + 1, size)
        --count
        ++modCount
    }

    override fun remove(element: E): Boolean{
        val index = indexOf(element)
        if (index == -1) return false
        removeAt(index);
        return true
    }

    fun indexOf( e: E): Int {
        for ( i in 0 until size )
            if (array[i] == e) return i
        return -1
    }

    override fun clear() {
        count = 0
        ++modCount
    }

    override fun contains(element: E): Boolean {
        this.forEach {
            if( it == element ) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<E>): Boolean {
       elements.forEach {
           if ( ! this.contains(it)) return false
       }
       return true
    }

    fun removeIf( pred: ( E)-> Boolean ): Boolean {
        var saved = 0
        for ( i in 0 until  size )
            if ( !pred(array[i]) )
                array[saved++] = array[i] // Não remove, copia
        if ( saved != size ) {
            count = saved
            return true
        }
        return false
    }
    override fun removeAll(elements: Collection<E>)  =
        removeIf { elements.contains( it )}

    override fun retainAll(elements: Collection<E>) =
        removeIf { !elements.contains( it )}

    override operator fun iterator(): MutableIterator<E> =
        object : MutableIterator<E> {
            var expectedModCount = modCount
            var index= 0
            var flagNext = false
            override fun hasNext(): Boolean = index < size
            override fun next(): E {
                if (expectedModCount != modCount)
                    throw ConcurrentModificationException()
                if(!hasNext())
                    throw NoSuchElementException("no more elements")
                flagNext= true
                return array[index++]

            }
            override fun remove() {
                if (expectedModCount != modCount)
                    throw ConcurrentModificationException ()
                check(flagNext){ "No next" }
                removeAt(index-1)
                --index
                flagNext = false
                expectedModCount= modCount
            }
        }

}