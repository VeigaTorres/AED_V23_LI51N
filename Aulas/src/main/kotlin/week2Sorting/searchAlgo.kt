package week2Sorting

/**
 * Procurar um valor igual num subarray.
 * Custo máximo O( n )
 * Custo minimo Omega( 1 )
 * @param array array
 * @param left - indice onde começa o subarray (inclusivo)
 * @param right - indice onde termina o subarray (inclusivo)
 * @param value valor a procurar
 * @return true caso o valor exista no subarray
 */
fun search(array: Array<Int>, value: Int,
           left: Int=0, right: Int=array.size-1): Boolean {
    for ( i in left .. right )
        if ( array[i] == value )
            return true;
    return false
}

/**
 * Procurar um valor igual num array - algoritmo recursivo
 * - Caso o valor nao seja o primeiro à esquerda procurar no restante subarray 'a direira
 * Profundidade de recursão O(n)
 * Custo máximo O( n )
 * Custo minimo Omega( 1 )
 */
fun searchTailRecursive(array: Array<Int>, value: Int,
                    left: Int=0, right: Int = array.size-1): Boolean {
    if ( left > right ) return false
    if ( array[ left] == value ) return true
    return searchTailRecursive(array, value, left+1, right)
}

/**
 * Procurar um valor igual num array - algoritmo recursivo
 * - Caso o valor nao seja o do meio procurar na primeira metade do subarray e caso não
 * exista na segunda metade do subarray.
 * Recorrencia -> C(1) = O(1); C(n) = 2xC(n/2) + O(1) -> resolvendo a recorrencia O(n)
 * Custo máximo O(n)
 * Custo minimo Omega( 1 )
 */
fun searchRecursive(array: Array<Int>, value: Int,
                    left: Int=0, right: Int = array.size-1): Boolean {
    TODO()
}

/**
 * Procurar um valor igual num array ordenado - versão recursiva
 * Recorrencia -> C(1) = O(1); C(n) = C(n/2) + O(1) -> resolvendo a recorrencia O(lg n)
 * Custo máximo O(lgn)
 * Custo minimo Omega( 1 )
 * @param sortedArray array ordenado
 * @param left           indice a partir do qual procura (inclusivo)
 * @param right           indice até onde procura (inclusivo)
 * @param value       valor a procurar no array
 * @return true se encontrar o valor false caso contrário
 */
fun searchBinaryRecursive(sortedArray: Array<Int>, value: Int,
                          left: Int=0, right: Int=sortedArray.size-1): Boolean {
    /* Pesquisa binária recursiva */
    if ( left > right) return false;
    val m = (left+right) ushr 1
    if ( sortedArray[m] == value) return true
    if( sortedArray[m] > value)
        return searchBinaryRecursive(sortedArray, value, left, m-1)
    else
        return searchBinaryRecursive(sortedArray, value, m+1, right)
}

fun searchBinary(sortedArray: Array<Int>, value: Int,
                 left: Int=0, right: Int=sortedArray.size-1): Boolean {
    /* Pesquisa binária iterativa */
    var l = left; var r=right;
    while( l <= r ) {
        val m= (l+r) ushr 1;
        if ( value == sortedArray[m] ) return true
        if ( value > sortedArray[m]  )
            l = m+1
        else
            r = m-1
    }
    return false
}

/**
 * Obter o indice do primeiro valor igual ou caso não exista o primeiro maior
 * Recorrencia -> C(1) = O(1); C(n) = C(n/2) + O(1) -> resolvendo a recorrencia O(lg n)
 * Custo máximo O(lg n)
 * Custo minimo Omega( lg n)
 * Custo Theta( lg n)
 * @param sortedArray array ordenado
 * @param left       indice a partir do qual procura (inclusivo)
 * @param right      indice até onde procura (inclusivo)
 * @param value      valor a procurar no array
 * @return indice do primeiro valor igual ou maior a value
 */
fun lowerBound(sortedArray: Array<Int>,value: Int, left: Int=0, right: Int=sortedArray.size-1): Int {
    var l = left;
    var r =right;
    while( l <= r ) {
        val m= (l+r) ushr 1;
        if ( sortedArray[m] >= value)
            r = m-1
        else
            l = m+1
    }
    return l
}

/**
 * Indice do primeiro valor maior
 * Recorrencia -> C(1) = O(1); C(n) = C(n/2) + O(1) -> resolvendo a recorrencia O(lg n)
 * Custo máximo O(lg n)
 * Custo minimo Omega(lg n)
 * Custo Theta(lg n)
 * @param sortedArray array ordenado
 * @param left           indice a partir do qual procura (inclusivo)
 * @param right           indice até onde procura (inclusivo)
 * @param value         valor a procurar no array
 * @return indice do primeiro valor maior do que value
 */
fun upperBound(sortedArray: Array<Int>, value: Int, left: Int, right: Int): Int {
    TODO()
}