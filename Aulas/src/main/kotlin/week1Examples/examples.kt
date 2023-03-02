package week1Examples
/**
 *  Sequência de Fibonacci:
 *   f(0) = 0
 *   f(1) = 1
 *   f( n ) = (f(n-1) + f (n -2) para n > 1
 * Complexidade:
 *   em termos de tempo -
 *   em termos de espaço extra -
 */
fun fibonacci_recursive(n:Int): Int{
    return 0
}

/**
 * Usando a técnica de memorização
 * Complexidade:
 *   em termos de tempo -
 *   em termos de espaço extra -
 */
fun fibonacci( n:Int ): Int {
    return 0
}

/**
 * Cálculo da potencia base^expoente, com base > 0 e expoente >= 0
 * Complexidade:
 *   em termos de tempo -
 *   em termos de espaço extra -
 */
fun powerIterative(base: Int, n: Int ):Int{
    return 0
}

/**
 * Profundidade de recursão - N
 * Complexidade:
 *   em termos de tempo -
 *   em termos de espaço extra -
 */
fun powerRecursiveDepthN(base: Int, n: Int ):Int {
    return 0
}

/**
 *  Cálculo da potencia:
 *   pow(b, 0) = 1
 *   pow(b, n) = pow(b, n/2)* pow(b, n/2) para n par
 *   pow(b, n) = b * pow(b, n/2)* pow(b, n/2) para n impar
 * Complexidade:
 *   em termos de tempo -
 *   em termos de espaço extra -
 */
fun powerRecursiveDepthLgN(base: Int, n: Int ):Int{
    return 0
}

/* Versão final
 * Complexidade:
 *   em termos de tempo -
 *   em termos de espaço extra -
 */
fun power(base: Int, n: Int ):Int{
    return 0
}

/**
 * Encontrar o subarray (l,r) no array tal que a soma dos seus elementos
 * seja a maior possível.
 * Retorna o Triple (l, r, sum)
 * Complexidade em termos de tempo   -
 * em termos de espaço extra -
 */

/* Versão final
 * Complexidade em termos de tempo -
 * em termos de espaço extra -
 */
fun maximumSubArrayQuad( array: Array<Double>, left: Int, right:Int ):Triple<Int,Int,Double> {
    if ( left > right )
        return Triple(left, right, 0.0)
    var res: Triple<Int, Int, Double> = Triple(left, left, array[left])

    return res
}

fun maximumSubArray( array:Array<Double>, left: Int, right:Int ):Triple<Int,Int,Double> {
    if ( left > right ) return Triple(left, right, 0.0)
    var res: Triple<Int, Int, Double> = Triple(left, left, array[left])

    return res
}
