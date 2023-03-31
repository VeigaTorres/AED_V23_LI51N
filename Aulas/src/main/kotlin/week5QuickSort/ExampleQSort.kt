package week5QuickSort

import kotlin.random.Random

data class Algorithm(val description: String, val algorithm: (Array<Int>, Int, Int, (Int, Int)->Int)-> Unit)
internal val sorts = arrayOf<Algorithm>(
   Algorithm("initial", ::quickSort0),
   Algorithm("semi iterative on right", ::quickSort1),
   Algorithm("semi iterative on left", ::quickSort2),
   Algorithm("semi iterative (right or left depending on the dimension)", ::quickSort),
   Algorithm("uses the median of 3 for the pivot",::quickSortWithMedian),
   Algorithm("hibrido usando 3 partições",::quickSortHybrid )
)

private val N: Int = 100000

data class InitFunc(val name: String, val get: (Int) -> Int) 
private val functions = arrayOf<InitFunc>(
    InitFunc("only with zeros (all equals)", { 0 }),
    InitFunc("sorted", { i -> i }),
    InitFunc("reversed sort", { i -> N - i }),
    InitFunc("random", { i -> Random.nextInt(N) })
)

fun main(args: Array<String>) {
    var arrayTest :Array<Int>
    var array :Array<Int>
    for (func in functions) {
        System.out.println("*** array ${func.name}")
        var index =0;
        for (sort in sorts)
            try {
                System.out.println("\t** sorted by: algorithm ${sort.description}")
                array = Array(N, func.get)
                sort.algorithm(array, 0, array.size-1, Int::compareTo)
                testSorted(array)
                println()
            }
            catch (e: Throwable) {
                println("\t-> " + e.toString())
            }
            finally { ++index }
    }
}

private fun testSorted(array: Array<Int>) {
    for (i in 1 until  array.size)
        if (array[i] < array[i - 1]) {
            print("\t -> NOT SORTED")
            return
        }
    print("\t -> SORTED")
}


