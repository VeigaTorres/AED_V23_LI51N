package week8Collection

public class ArrayCollection<E>( capacityInicial: Int = 10 ):  RandomAccess {//MutableCollection<E>,{
//<< Variáveis de instância >>
    private var count = 0       // Número de elementos contidos.
    //private var capacity: Int = capacityInicial  // Dimensão do array.
    private var array: Array<E> = allocate( capacityInicial )// Bloco de memória alojado.

    val capacity: Int get() = array.size
    val size: Int get() = count

    @Suppress("UNCHECKED_CAST")
    private fun  allocate(n: Int ): Array<E> =
        arrayOfNulls<Any>(n) as Array<E>

    fun add( i: Int, e: E) {
        require( i <= size )
        val aux = array;
        if ( size == capacity ) {
            array = allocate( capacity * 2 )
            aux.copyInto(array, 0, 0, i);
        }
        aux.copyInto(array,i+1, i, size);
        array[i] = e;
        ++count;
    }

    fun add( e: E ): Boolean{
        add(size, e)
        return true
    }

    fun removeAt(i: Int) {
        require(i < size)
        array.copyInto(array, i, i + 1, size)
        --count
    }

    fun remove(e: E): Boolean{
        val index = indexOf(e)
        if (index == -1) return false
        removeAt(index);
        return true
    }

    fun indexOf( e: E): Int {
        for ( i in 0 until size )
            if (array[i] == e) return i
        return -1
    }

    fun clear() { count = 0 }

    operator fun iterator(): MutableIterator<E> =
        object : MutableIterator<E> {
            override fun hasNext(): Boolean {
                TODO("Not yet implemented")
            }

            override fun next(): E {
                TODO("Not yet implemented")
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
}