package week2Sorting

import week1Examples.*
import kotlin.random.Random

/* Gerar um array de n Double positivos e negativo
 * Usa o método Ramdom.nextDouble( min, max). O valor
 * do min e do max  podem ser negativos ou positivos
*/
fun generateRandomArray(n:Int):Array<Double> = TODO()

/* Gerar um array de n Double valores negativos
 * decrescentes
*/
fun generateNegativeArray(n: Int): Array<Double> = TODO()

/* Gerar um array de n Double valores positivos
*/
fun generatePositiveArray(n: Int): Array<Double> = TODO()

fun testMaximumSubArray(dims: Array<Int>,
                        gen: (n:Int)-> Array<Double>,
                        functionTest: (Array<Double>, l:Int, r:Int)->Triple<Int,Int,Double>) {
    for( i in dims.indices ) {
        var time:Long

         // Gerar o array e execurar o funcão em teste 11 vezes
         //Para obter o tempo de inicio e o de fim usar
         //   System.currentTimeMillis() que retorna um Long
         TODO()

        println("${dims[i]} $time")
    }
}

fun main() {
    val dimsQuadratico = arrayOf( 10000, 20000, 40000, 80000, 160000)
    val dimsLinear = arrayOf( 2000000, 4000000, 8000000, 16000000, 32000000)
    println("----- MaxSubArray- all positives --")
    testMaximumSubArray( dimsQuadratico, ::generatePositiveArray, ::maximumSubArray)
    println("----- MaxSubArray- all negatives and decrescent --")
    testMaximumSubArray( dimsQuadratico, ::generateNegativeArray, ::maximumSubArray)

    TODO()
}