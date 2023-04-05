package week6LinearSort

import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

/**
 * Algoritmo de ordena��o em tempo linear
 * @param a array a ordenar
 * @param b array com os elementos do array a ordenados
 * @param n n�mero de elementos a ordenar 
 * @param k cada um dos elementos do array a � um inteiro entre 0..k
 */
private fun countingSort(a: Array<Int>, b: Array<Int>, n:Int, k: Int) {
    TODO()
}
/**
 * Algoritmo de ordena��o em tempo linear.
 * O valor m�ximo no array tem que ser menor que n
 * @param a array a ordenar
 */
fun countingSort( a: Array<Int> ) {
    if (a.size > 1) {
        val k = a.max()
        require(k <= a.size) { "maximum is greater than length" }
        val b = Array(a.size) { 0 }
        countingSort(a, b, a.size, k)
        b.copyInto(a)
    }
}

/**
 * Algoritmo que ordena os rBits a partir da posi��o digitNumber*rbits.
 * @param a array de inteiros positivos
 * @param b array ordenado pelos rBits a partir
 * da posi��o digitNumber*rbits
 * @param digitNumber - digito pelo qual se pretende ordenar,
 *                      em que um digito � um conjunto de rBits.
 * @param rBits n�mero de bits de um digito
 */
private fun countingRadixSort( a: Array<Int>, b: Array<Int>,
                               digitNumber: Int, rBits: Int) {
    // Valor m�ximo com rBits. Ser� usado como masc�ra dos rBits
    val mask = (1 shl rBits) - 1 // == k == 2^rBits -1
    // Numero de shifts sabendo que cada dig�to ocupa rBits
    val shift = digitNumber * rBits
    val c = Array(mask + 1) {0};
    TODO()
}

/**
 * Algoritmo de ordena��o radix sort
 * @param v array de inteiros positivos
 */
fun radixSort(v: Array<Int>) {
    if (v.size <= 1) return
    val r_bits = 31 - v.size.countLeadingZeroBits()  //log2(v.size)
    val d_digits = ceil(32.0 / r_bits).toInt()
    var a = v
    var b = Array(v.size){0}
    for (d in 0 until d_digits) {
        countingRadixSort(a, b, d, r_bits)
        // Trocar a com b
        val aux = a
        a = b 
        b = aux 
    }
    if (a != v) a.copyInto( v )
}



