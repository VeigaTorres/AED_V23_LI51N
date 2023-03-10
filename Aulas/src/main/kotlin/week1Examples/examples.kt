package week1Examples
/**
 *  Sequência de Fibonacci:
 *   f(0) = 0
 *   f(1) = 1
 *   f( n ) = (f(n-1) + f(n -2) para n > 1
 * Complexidade:
 *   em termos de tempo - O(2^n)
 *   em termos de espaço extra - O(n)
 */
fun fibonacci_recursive(n:Int): Int{
    return if ( n <= 1 )  n
           else fibonacci_recursive(n-1) + fibonacci_recursive( n-2)
}

/**
 * Usando a técnica de memorização
 * Complexidade:
 *   em termos de tempo - O(n)
 *   em termos de espaço extra - O(n)
 */
fun fibonacci( n:Int ): Int {
    if ( n <= 1 )  return n
    val array = Array(n+1){0}
    array[1] = 1
    for( i in 2 .. n )
        array[i] = array[i-1]+ array[i-2]
    return array[n]
}

/**
* Trabalho de casa
* Complexidade:
*   em termos de tempo - O(n)
*   em termos de espaço extra - O(1)
*/
fun fibonacci_final( n:Int ): Int {
    if ( n <= 1 )  return n
    val array = arrayOf(0, 1)
    for( i in 2 .. n ) {
        val aux = array[0] + array[1]
        array[0] = array[1]
        array[1] = aux;
    }
    return array[1]
}


/**
 * Cálculo da potencia base^expoente, com base > 0 e expoente >= 0
 * Complexidade:
 *   em termos de tempo - O(n)
 *   em termos de espaço extra - O(1)
 */
fun powerIterative(base: Int, n: Int ):Int{
    var res= 1
    for ( i in 1 .. n)
        res*= base
    return res
}

/**
 * Profundidade de recursão - N
 * Complexidade:
 *   em termos de tempo - O(n)
 *   em termos de espaço extra - O(n)
 */
fun powerRecursiveDepthN(base: Int, n: Int ):Int {
    if ( n == 0 ) return 1
    return base * powerRecursiveDepthN( base, n-1)
}

/**
 *  Cálculo da potencia:
 *   pow(b, 0) = 1
 *   pow(b, n) = pow(b, n/2)* pow(b, n/2) para n par
 *   pow(b, n) = b * pow(b, n/2)* pow(b, n/2) para n impar
 * Complexidade:
 *   em termos de tempo - O(n)
 *   em termos de espaço extra - O(lg n)
 */
fun powerRecursiveDepthLgN(base: Int, n: Int ):Int{
    if( n == 0 ) return 1
    else if ( n % 2 == 0 ) return powerRecursiveDepthLgN(base, n/2) * powerRecursiveDepthLgN(base, n/2)
    else return powerRecursiveDepthLgN(base, n/2) * powerRecursiveDepthLgN(base, n/2)* base
}

/* Versão final
 * Complexidade:
 *   em termos de tempo - O(lg n)
 *   em termos de espaço extra - O(lg n)
 */
fun power(base: Int, n: Int ):Int{
    if( n == 0 ) return 1
    val aux =power(base, n/2)
    if ( n % 2 == 0 ) return  aux* aux
    else return aux* aux * base
}

/**
 * Encontrar o subarray (l,r) no array tal que a soma dos seus elementos
 * seja a maior possível.
 * Retorna o Triple (l, r, sum)
 * Complexidade:
 *   em termos de tempo - O(n^2)
 *   em termos de espaço extra - O(1)
 */
fun maximumSubArrayQuad( array: Array<Double>, left: Int, right:Int ):Triple<Int,Int,Double> {
    if ( left > right )
        return Triple(left, right, 0.0)
    var res: Triple<Int, Int, Double> = Triple(left, left, array[left])
    for( l in left .. right) {
        var sum = 0.0
        for (r in l..right) {
            sum+= array[ r ]
            if( sum > res.third) res = Triple(l, r, sum)
        }
    }

    return res
}

/* Versão final - Trabalho de casa
 * Complexidade em termos de tempo - O(n)
 * em termos de espaço extra - O(1)
 */
fun maximumSubArray( array:Array<Double>, left: Int, right:Int ):Triple<Int,Int,Double> {
    if ( left > right )
        return Triple(left, right, 0.0)
    var res: Triple<Int, Int, Double> = Triple(left, left, array[left])
    var sum = 0.0
    var l = left
    for (r in left..right) {
            sum+= array[ r ]
            if( sum > res.third) res = Triple(l, r, sum)
            if ( sum < 0 ) {
                sum = 0.0
                l = r + 1
            }
    }

    return res
}
