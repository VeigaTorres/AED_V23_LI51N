package week6LinearSort

import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

/**
 * Algoritmo de ordenação em tempo linear
 * @param a array a ordenar
 * @param b array com os elementos do array a ordenados
 * @param n número de elementos a ordenar 
 * @param k cada um dos elementos do array a é um inteiro entre 0..k
 */
private fun countingSort(a: Array<Int>, b: Array<Int>, n:Int, k: Int) {
    val c = Array(k+1){ 0 }
    for( i in 0 until n ) {
        //val indexedVal = a[i]
        ++c[ a[ i ]]
    }
    for( i in 1 until c.size)
        c[i] += c[i-1]

    for( i in n-1 downTo  0) {
        --c[ a[i] ]
        val positionFinal = c[ a [i] ]
        b[ positionFinal ] = a[i]
    }
}
/**
 * Algoritmo de ordenação em tempo linear.
 * O valor máximo no array tem que ser menor que n
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
 * Algoritmo que ordena os rBits a partir da posição digitNumber*rbits.
 * @param a array de inteiros positivos
 * @param b array ordenado pelos rBits a partir
 * da posição digitNumber*rbits
 * @param digitNumber - digito pelo qual se pretende ordenar,
 *                      em que um digito é um conjunto de rBits.
 * @param rBits número de bits de um digito
 */
private fun countingRadixSort( a: Array<Int>, b: Array<Int>,
                               digitNumber: Int, rBits: Int) {
    // Valor máximo com rBits. Será usado como mascára dos rBits
    val mask = (1 shl rBits) - 1 // == k == 2^rBits -1
    // Numero de shifts sabendo que cada digíto ocupa rBits
    val shift = digitNumber * rBits
    val c = Array(mask + 1) {0};
    //for ( i in 0 until a.size-1) {//Na aula estava errado com o until deve ser a.size()
    for ( i in 0 until a.size) {
        val index = a[i] shr shift and mask
        ++c[ index ]
    }
    for( i in 1 until c.size)
        c[i] += c[i-1]

    for ( i in a.size-1 downTo  0) {
        val index = a[i] shr shift and mask
        -- c[ index]
        b[ c[index ]] = a[i]
    }

}

/**
 * Algoritmo de ordenação radix sort
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



