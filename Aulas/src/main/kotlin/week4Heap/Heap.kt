package week4Heap

import week2Sorting.exchange

/**
 * Obter o indice do ascendente de qualquer n� dum heap representado
 * em array
 * @param i indice do n�
 * @return  o indice do ascendente do n� i
 */
fun parent(i: Int): Int  = (i - 1) shr 1

/**
 * Obter o indice do descendente esquerdo de qualquer n� dum heap representado em array
 * @param i indice do n�
 * *
 * @return  o indice do descendente esquerdo do n� i
 */
fun left(i: Int): Int = (i shl 1) + 1

/**
 * Obter o indice do descendente direito de qualquer n� dum heap representado em array
 * @param i indice do n�
 * *
 * @return  o indice do descendente esquerdo do n� i
 */
fun right(i: Int): Int = (i shl 1) + 2

/**
 * Construir um maximo heap, segundo um determinado comparador,
 * com raiz no n� i sabendo que as sub-�rvores
 * � direita e � esquerda de i s�o maximo heaps.
 * Complexidade � O(lg n)
 * @param heap   - heap representado em array
 * @param heapSize - n�mero de n�s do heap
 * @param i - indice do n� raiz do heap
 * @param compare - fun��o de compara��o
 */
tailrec fun <T>  maxHeapify(heap: Array<T>, heapSize: Int, i: Int,
                                compare: (T, T)-> Int) {
    val l= left(i)
    val r= right(i)
    var largest: Int
    largest = if ( l < heapSize && compare(heap[l], heap[i]) > 0) l else i
    if ( r < heapSize && compare(heap[r],  heap[largest]) > 0) largest= r
    if ( i != largest) {
        heap.exchange( i, largest)
        maxHeapify(heap, heapSize, largest, compare)
    }
} // T(N) = T(n/2) +  O ( 1 ) = O( lg n)

/**
 * Construir um minimo heap com raiz no n� i sabendo que as sub-�rvores
 * � direita e � esquerda de i s�o minimos heaps: O(lg n)
 * @param heap   - heap representado em array
 * *
 * @param heapSize - n�mero de n�s do heap
 * *
 * @param i - indice do n� raiz do heap
 */
fun <T> minHeapify(heap: Array<T>, heapSize: Int, i: Int,
                   compare: (T, T)-> Int) {

    maxHeapify(heap, heapSize,i ){
            a, b -> compare( b, a)
    }
}

/**
 * Organizar os primeiros n valores de um array de forma
 * a que o array represente um heap ( O(n) )
 * @param a array com os valores a reorganizar
 * @param n  n�mero de n�s a reorganizar
 */
fun <T> buildMaxHeap(a: Array<T>, n: Int=a.size,
                     compare:(T, T)->Int) {
    for ( i in parent( n-1)  downTo  0)
        maxHeapify(a, n , i, compare)
}

/**
 * Organizar os valores do array de forma a que representem um heap
 * @param a array valores a organizar em heap
 */
fun <T> buildMinHeap(a: Array<T>, n: Int=a.size, compare:(T, T)->Int) {
    buildMaxHeap(a, n) { a, b -> compare(b, a)}
}

/**
 * Ordenar o array por ordem crescente de valores pelo "algoritmo heap sort"
 * - ( no pior caso � O(n lg n), no melhor O(n) )
 * @param a array com os valores a ordenar
 */
fun <T> heapSort(a: Array<T>, compare:(T, T)->Int) {
    buildMaxHeap(a, a.size, compare) // Na aula n�o chamamos o build
    for ( i in a.size-1 downTo  1){
        a.exchange(i, 0)
        maxHeapify(a, i, 0, compare)
    }

}   // T(N) = O(n) +  n x O( lg n ) = O(n) + O( n lg n) =  O(n + n lg n) = O(n lg n)

/**
 * Alterar para um valor superior o valor de um n�, isto �,
 * heap[index] > v.
 * @param heap heap representado em array
 * @param index indice do n� cujo valor vai ser alterado para maior
 * @param value valor a inserir
 */
fun <T> heapIncreaseKey(heap: Array<T>, index: Int, value: T, compare: (T, T) -> Int) {
    heap[index] = value
    var i = index
    var p= parent( i )
    while ( i > 0 && compare(heap[i], heap[p]) > 0) {
        heap.exchange(i, p)
        i = p
        p= parent(i)
    }
}

/**
 * Retira da estrura array o maximo valor
 * @param heap array representado em array
 * @param heapSize numero de n�s do array
 * @return o valor retirado
 */
fun <T> extractMaxHeap(heap: Array<T>, heapSize: Int, compare: (T, T) -> Int): T {
    val max = heap[0]
    heap[0] = heap[heapSize-1]
    maxHeapify(heap, heapSize-1, 0, compare)
    return max
}
