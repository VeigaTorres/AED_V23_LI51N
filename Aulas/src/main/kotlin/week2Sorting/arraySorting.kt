package week2Sorting

fun Array<Int>.exchange(i1: Int, i2: Int) {
    val aux = get(i1)
    set(i1, get(i2))
    set(i2, aux)
}

/**
 * Ordena um subarray pelo algoritmo insertion sort. Algoritmo estável
 * Melodogia - por cada iteração INSERIR ordenadamente cada valor
 * Compexidade -
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun insertionSort(a:Array<Int>, left:Int=0, right: Int=a.size-1){

}

/**
 * Ordena um subarray pelo algoritmo bubble sort. Algoritmo ESTÀVEL
 * Compexidade -
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun bubbleSort(a:Array<Int>, left: Int=0, right: Int=a.size-1) {//Theta(n^2)

}

/**
 * Ordena um subarray pelo algoritmo bubble sort. Algoritmo ESTÀVEL.
 * Quando deteta que já está ordenada termina a ordenação.
 * Compexidade -
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun bubbleSortFlag(a:Array<Int>, left: Int=0, right: Int=a.size-1) {
    // Trabalho de casa
}

/**
 * Ordena um subarray pelo algoritmo selection sort. NÂO é ESTÁVEL
 * Metodogia - por cada iteração SELECIONAR o menor
 * complexidade -
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun selectionSort(a:Array<Int>, left: Int=0, right: Int=a.size-1) {

}

/**
 * Ordena um subarray pelo algoritmo utils.merge sort. Algoritmo ESTÀVEL.
 * Compexidade - O(n x lg n)
 * @param a - array
 * @param l - indice onde começa o subarray (inclusivo)
 * @param r - indice onde termina o subarray (inclusivo)
 */
fun mergeSort(a:Array<Int>, l: Int=0, r: Int = a.size-1 ) {
}

/**
 * Produz um array ordenado a partir de dois subarrays consecutivos ordenados
 * @param array array
 * @param l1 - indice onde começa o primeiro subarray ordenado (inclusivo)
 * @param m - indice onde termina o primeiro subarray ordenado (inclusivo)
 * @param r2 - indice onde termina o segundo subarray ordenado (inclusivo)
 */
fun merge(array: Array<Int>, l1: Int, m: Int, r2: Int) {

}