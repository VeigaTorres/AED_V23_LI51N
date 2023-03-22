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
fun maxHeapify(heap: Array<Int>, heapSize: Int, i: Int) {

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
    TODO()
}

/**
 * Organizar os primeiros n valores de um array de forma
 * a que o array represente um heap ( O(n) )
 * @param a array com os valores a reorganizar
 * @param n  n�mero de n�s a reorganizar
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
 * - ( no pior caso � O(n lg n), no melhor O(n) )
 * @param a array com os valores a ordenar
 */
fun <T> heapSort(a: Array<T>, compare:(T, T)->Int) {
    TODO()
}   // T(N) = O(n) +  n x O( lg n ) = O(n) + O( n lg n) =  O(n + n lg n) = O(n lg n)

/**
 * Alterar para um valor superior o valor de um n�, isto �,
 * heap[index] > v.
 * @param heap heap representado em array
 * @param index indice do n� cujo valor vai ser alterado para maior
 * @param value valor a inserir
 */
fun <T> heapIncreaseKey(heap: Array<T>, index: Int, value: T, compare: (T, T) -> Int) {
    TODO()
}

/**
 * Retira da estrura array o maximo valor
 * @param heap array representado em array
 * @param heapSize numero de n�s do array
 * @return o valor retirado
 */
fun <T> extractMaxHeap(heap: Array<T>, heapSize: Int, compare: (T, T) -> Int): T {
    TODO()
}
