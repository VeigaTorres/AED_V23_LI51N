package week4Heap

import week2Sorting.exchange

/**
 * Obter o indice do ascendente de qualquer nó dum heap representado
 * em array
 * @param i indice do nó
 * @return  o indice do ascendente do nó i
 */
fun parent(i: Int): Int  = (i - 1) shr 1

/**
 * Obter o indice do descendente esquerdo de qualquer nó dum heap representado em array
 * @param i indice do nó
 * *
 * @return  o indice do descendente esquerdo do nó i
 */
fun left(i: Int): Int = (i shl 1) + 1

/**
 * Obter o indice do descendente direito de qualquer nó dum heap representado em array
 * @param i indice do nó
 * *
 * @return  o indice do descendente esquerdo do nó i
 */
fun right(i: Int): Int = (i shl 1) + 2

/**
 * Construir um maximo heap, segundo um determinado comparador,
 * com raiz no nó i sabendo que as sub-árvores
 * à direita e à esquerda de i são maximo heaps.
 * Complexidade é O(lg n)
 * @param heap   - heap representado em array
 * @param heapSize - número de nós do heap
 * @param i - indice do nó raiz do heap
 * @param compare - função de comparação
 */
fun maxHeapify(heap: Array<Int>, heapSize: Int, i: Int) {

} // T(N) = T(n/2) +  O ( 1 ) = O( lg n)

/**
 * Construir um minimo heap com raiz no nó i sabendo que as sub-árvores
 * à direita e à esquerda de i são minimos heaps: O(lg n)
 * @param heap   - heap representado em array
 * *
 * @param heapSize - número de nós do heap
 * *
 * @param i - indice do nó raiz do heap
 */
fun <T> minHeapify(heap: Array<T>, heapSize: Int, i: Int,
                   compare: (T, T)-> Int) {
    TODO()
}

/**
 * Organizar os primeiros n valores de um array de forma
 * a que o array represente um heap ( O(n) )
 * @param a array com os valores a reorganizar
 * @param n  número de nós a reorganizar
 */
fun <T> buildMaxHeap(a: Array<T>, n: Int=a.size,
                     compare:(T, T)->Int) {
    TODO()
}

/**
 * Organizar os valores do array de forma a que representem um heap
 * @param a array valores a organizar em heap
 */
fun <T> buildMinHeap(a: Array<T>, n: Int=a.size, compare:(T, T)->Int) {
    TODO()
}

/**
 * Ordenar o array por ordem crescente de valores pelo "algoritmo heap sort"
 * - ( no pior caso é O(n lg n), no melhor O(n) )
 * @param a array com os valores a ordenar
 */
fun <T> heapSort(a: Array<T>, compare:(T, T)->Int) {
    TODO()
}   // T(N) = O(n) +  n x O( lg n ) = O(n) + O( n lg n) =  O(n + n lg n) = O(n lg n)

/**
 * Alterar para um valor superior o valor de um nó, isto é,
 * heap[index] > v.
 * @param heap heap representado em array
 * @param index indice do nó cujo valor vai ser alterado para maior
 * @param value valor a inserir
 */
fun <T> heapIncreaseKey(heap: Array<T>, index: Int, value: T, compare: (T, T) -> Int) {
    TODO()
}

/**
 * Retira da estrura array o maximo valor
 * @param heap array representado em array
 * @param heapSize numero de nós do array
 * @return o valor retirado
 */
fun <T> extractMaxHeap(heap: Array<T>, heapSize: Int, compare: (T, T) -> Int): T {
    TODO()
}
