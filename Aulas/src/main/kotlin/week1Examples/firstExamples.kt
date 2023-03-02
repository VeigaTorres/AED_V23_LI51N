package week1Examples

/**
 * Retorna o indice onde se encontra um determinado valor no array
 * de inteiros. Caso não exista retorna -1.
 * Complexidade:
 *   em termos de tempo - O(n)
 *   em termos de espaço extra - O(1)
 * @param array - array de inteiros
 * @param value - inteiro a procurar
 * @return o indice
 */
fun indexOf(array:Array<Int>, value:Int ):Int {
    for( i in 0 until  array.size )
        if( value == array[i] ) return i
    return -1
}

/**
 * Função extensao de Array<Int> que retorna o indice onde se
 * encontra um determinado valor no array de inteiros.
 * Caso não exista retorna -1.
 * @param value - inteiro a procurar
 * @return o indice
 */
fun Array<Int>.index( value: Int ) : Int {
    for ( i in 0 until  this.size )
        if ( value == this[ i ])
            return i
    return -1
}

/**
 * Função extensao de Array<String> que retorna o indice onde
 * se encontra uma determinada string no array de Strings.
 * Caso não exista retorna -1.
 * @param value - string a procurar
 * @return o indice
 */
fun Array<String>.index( value: String) : Int {
    for ( i in 0 until  this.size )
        if ( value == this[ i ])
            return i
    return -1
}
/**
 * Função genérica extensao de Array<T>, onde T representa qualquer tipo.
 * Retorna o indice onde se encontra um determinado valor.
 * Caso não exista retorna -1.
 * @param value - valor a procurar
 * @return o indice
 */
fun <T> Array<T>.index( value : T ) : Int {
    for ( i in 0 until size )
        if ( value == this[i] )
            return i
    return -1
}

/**
 * Algoritmo que retorna true caso um determinado valor exista no array.
 * Caso contrário retorna false.
 * @param array - array
 * @return true caso exista
 */
fun contains (array:Array<Int>, value:Int ): Boolean= indexOf(array, value) != -1

/**
 * Retorna o valor máximo existente num array de inteiros.
 * Complexidade:
 *   em termos de tempo - O(n)
 *   em termos de espaço extra - O(1)
 * @param array - array
 * @return o valor máximo
 */
fun max( array: Array<Int> ):Int {
    require ( !array.isEmpty()) { "Array empty" }
    var value = array[0]
    for ( i in 1 until array.size)
        if( value.compareTo( array[i]) <0 ) value = array[i]
    return value;
}

/**
 * Algoritmo genérico que retorna o valor máximo existente no array
 * segundo determinada função de comparação.
 * @param array - array
 * @param compare - função de comparação
 *   Sendo e1 e e2 dois elementos do tipo T a função compare(e1, e2)
 *   retorna um valor:
 *      > 0 se e1 > e2
 *      < 0 se e1 < e2
 *      == 0 se e1 == e2
 * @return o valor máximo segundo a função de comparação
 */
fun <T> max( array: Array<T>, compare: (T, T)->  Int):T {
    require(!array.isEmpty()){"Array empty"}
    var value = array[0]
    for ( i in 1 until array.size)
        if(compare( value, array[i]) < 0 ) value = array[i]
    return value;
}



