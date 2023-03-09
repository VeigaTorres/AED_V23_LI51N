package week2Sorting
fun <T> Array<T>.exchange(i1: Int, i2: Int) {
    val aux = this[i1]
    this[i1] = this[i2]
    this[i2] = aux
}

/**
 * Ordena um subarray pelo algoritmo insertion sort. Algoritmo estável
 * Melodogia - por cada iteração INSERIR ordenadamente cada valor
 * Compexidade - O(n2), no melhor caso O(n)
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun insertionSort(a:Array<Int>, left:Int=0, right: Int=a.size-1){
    for( i in left+1 .. right) {
        val key = a[i]
        var j = i -1
        while ( j >= left  && a[j] > key) {
            a.exchange(j, j + 1)
            --j
        }
        a[j+1] = key
    }
}

/* Algoritmo genérico */
fun <T> insertionSort(a:Array<T>, left:Int=0, right: Int=a.size-1,
                       compare: (T,T)-> Int) {
    for( i in left+1 .. right) {
        val key = a[i]
        var j = i -1
        while ( j >= left  && compare(a[j], key ) > 0) {
            a.exchange(j, j + 1)
            --j
        }
        a[j+1] = key
    }
}

fun sortCrescent( a: Array<Int>) {
    /*insertionSort(a, 0, a.size-1) { v1, v2 ->
         if ( v1 > v2 ) 100 else if ( v1 < v2 )  -12 else 0
    }
    insertionSort(a, 0, a.size-1) { v1, v2 -> v1.compareTo(v2) }
    */
    insertionSort(a, 0, a.size-1, Int::compareTo)
}

fun sortDeCrescent( a: Array<Int>) {
    /*
    insertionSort(a, 0, a.size-1) { v1, v2 ->
        if ( v1 < v2 ) 100 else if ( v1 > v2 ) -12 else 0
    }
    */
    insertionSort(a, 0, a.size-1) { v1, v2 -> v2.compareTo(v1) }
}

/**
 * Ordena um subarray pelo algoritmo bubble sort. Algoritmo ESTÀVEL
 * Compexidade - O(n^2)
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun bubbleSort(a:Array<Int>, left: Int=0, right: Int=a.size-1) {//Theta(n^2)
    for( i in left until right)
        for( j in right downTo i+1)
            if ( a[ j ] < a[j-1])
                a.exchange( j , j-1)
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
    TODO()
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
    for( i in left until right) {
        var index = i
        for ( j in i+1 .. right)
            if ( a[index] > a[j])
                index = j
        a.exchange( i, index)
    }
}

/**
 * Ordena um subarray pelo algoritmo utils.merge sort. Algoritmo ESTÀVEL.
 * Compexidade - O(n x lg n)
 * @param a - array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 */
fun mergeSort(a:Array<Int>, left: Int=0, right: Int = a.size-1 ) {
  TODO()
}

/**
 * Produz um array ordenado a partir de dois subarrays consecutivos ordenados
 * @param a array
 * @param left - indice onde começa o primeiro subarray ordenado (inclusivo)
 * @param m - indice onde termina o primeiro subarray ordenado (inclusivo)
 * @param right - indice onde termina o segundo subarray ordenado (inclusivo)
 */
fun merge(a: Array<Int>, left: Int, m: Int, right: Int) {
   TODO()
}