package week5QuickSort

import week2Sorting.*
import week4Heap.buildMaxHeap

fun <T> partition( a: Array<T>, l: Int=0, r: Int=a.size-1, compare:(T,T)->Int ) =
    partitionLomuto(a, l, r, compare)
/**
 * 1ª Versão recursiva do algoritmo quickSort
 * Profundidade de recursão: O(n) Omega(lg n)
 */
fun <T> quickSort0(a: Array<T>, l: Int=0, r: Int=a.size-1, compare:(T,T)->Int) {
    if ( r <= l ) return
    val q = partition(a, l, r, compare)
    quickSort0( a, l, q-1, compare)
    quickSort0(a, q+1, r, compare)
}

/**
 * Parte o array em duas partes (algoritmo de Lomuto).
 * Sendo q o indíce da partição:
 * a[l .. q-1] <= a[q]
 * a[q+1 .. r] >  a[q]
 * Parte o array em duas partes.
 * +----------------------------------------------------------+
 * |  pivot <=           | pivot |     > pivot                |
 * +----------------------------------------------------------+
 *                        ^q
 * @param a array a dividir
 * @param l indice a partir do qual é feita a partição (inclusivo)
 * @param r indice até onde é feita a partição (inclusivo)
 * @return indice da partição
 */
fun <T> partitionLomuto(a: Array<T>, l: Int, r: Int,
                        compare:(T,T)->Int) : Int {
    /*
    // +----------------------------------------------------------+
    // |   pivot <=          |    > pivot      |      ?           |
    // +----------------------------------------------------------+
    //                      ^i                  ^j               ^r
    */
    // 	Invariantes do ciclo
    // 	  a[l .. i] <= pivot
    //    a[i+1 .. j] > pivot
    //    a[j .. r-1] em análise
    var i = l-1
    val pivot = a[r]
    for( j in l until  r) {
        if (compare(a[j], pivot) <= 0) {
            ++i
            a.exchange(i, j)
        }
    }
    a.exchange(i+1, r)
    return i+1
}

/**
 * QuickSort - versão semi iterativa na direita
 * profundidade máxima da recursão: O(n)
 */
fun <T>quickSort1(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T, T) -> Int) {
    var l = left
    val r = right
    while ( l < r) {
        val q = partition(a, l, r, compare)
        quickSort1(a, l, q - 1, compare)
        l = q+1
     }
}

/**
 * QuickSort - versão semi iterativa na esquerda
 * profundidade máxima da recursão: O(n)
 */
fun <T>quickSort2(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T, T) -> Int) {
    val l = left
    var r = right
    while ( l < r) {
        val q = partition(a, l, r, compare)
        quickSort2(a, q+1, r, compare)
        r = q-1
    }
}

/**
 * QuickSort - versão semi iterativa
 * profundidade máxima da recursão: O(lg n)
 */
fun <T> quickSort(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T, T) -> Int) {
    var l = left
    var r = right
    while ( l < r ) {
        val q = partition(a, l, r, compare)
        // Verificar os tamanhos dos dois sub arrays e
        // chamar recursivamente o menor
        if ( q-l > r-q) {
            quickSort(a,  q+1, r, compare)
            r = q-1
        }
        else {
            quickSort(a, l, q-1, compare)
            l = q+1
        }
    }
 }

/**
 * QuickSort - versão iterativa
 * profundidade máxima da recursão: O(lg n)
 */
private fun <T> ArrayList<T>.push(t: T) = add(t)
private fun <T> ArrayList<T>.pop():T=  removeLast()

fun <T> quickSortIterative(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T, T) -> Int) {
    val stack = ArrayList<Int>()
    stack.push(left); stack.push( right )
    while ( !stack.isEmpty()) {
        // No topo está sempre o menor
        val r = stack.pop(); val l = stack.pop()
        if ( l < r ) {
            val q = partitionLomuto(a, l, r, compare)
            // Verificar os tamanhos dos dois sub arrays e
            // ordenar primeiro o menor
            if ( q-l > r-q) { // Resolver primeiro os arrays menores
                stack.push(l); stack.push(q - 1) //colocar no stack o maior
                stack.push(q+1); stack.push( r) //colocar no stack o menor
            }
            else {
                stack.push(q + 1); stack.push(r) //colocar no stack o maior
                stack.push(l); stack.push(q - 1) //colocar no stack o menor
            }
        }
    }
}

/**
 * QuickSort - profundidade máxima da recursão O(lg(n))
 * usa a MEDIANA de três
 * @param a array a ordenar
 * @param left indice a partir do qual é feita a ordenação (inclusivo)
 * @param right indice até onde é feita a ordenação (inclusivo)
 */
fun <T> quickSortWithMedian(a: Array<T>, left: Int=0, right: Int=a.size-1,
                            compare: (T, T) -> Int) {
    var l = left
    var r = right
    while (l < r) {
        // Calcular a mediana de 3 valores: a[l], a[r], a[middle]
        val m = (r+l) ushr 1
        a.exchange(m, r-1)
        if ( compare(a[l], a[r-1]) > 0) a.exchange(l, r-1)
        if( compare(a[l], a[r]) > 0) a.exchange(l, r)
        if ( compare(a[r-1], a[r]) > 0 ) a.exchange(r-1, r )
         // O maior foi colocado em a[r] o menor em a[l] e o do meio em a[r-1]
        if ( r-l <= 2) return
        val q = partition(a, l+1, r-1, compare)
        // A parte menor é ordenada recursivamente.
        if (q-l > r-q) {
            quickSortWithMedian(a, q + 1, r, compare)
            r = q - 1
        }
        else {
            quickSortWithMedian(a, l, q-1, compare)
            l= q+1
        }

     }
}

/**
 * Parte o array em duas partes (algoritmo de Hoare). Sendo q o indíce da partição:
 * a[l .. q-1] <= a[q]
 * a[q+1 .. r] >  a[q]
 * +----------------------------------------------------------+
 * |  pivot <            | pivot |     >= pivot               |
 * +----------------------------------------------------------+
 * ^q
 * @param a array a dividir
 * *
 * @param l indice a partir do qual é feita a partição (inclusivo)
 * *
 * @param r indice até onde é feita a partição (inclusivo)
 * *
 * @return indice da partição
 */
fun <T> partitionHoare(a: Array<T>, l: Int=0, r: Int=a.size-1, compare: (T, T)-> Int): Int {
    // +----------------------------------------------------------+
    // |   pivot <         |        ?      |  >= pivot      |pivot|
    // +----------------------------------------------------------+
    //                      ^i            ^j                  ^r
    // 	Invariantes do ciclo
    // 	  a[l .. i-1]   < pivot
    //    a[j+1 .. r-1] >= pivot
    //    a[i .. j]     em análise
    var i= l
    var j = r-1
    val pivot = a[r]
    while ( i <= j) {
        while (compare(a[i], pivot) < 0) ++i
        while (j >= i && compare(a[j], pivot) > 0 ) --j
        if (j >= i ) {
            a.exchange(i, j)
            ++i
            --j
        }
    }
    a.exchange( i, r)
    return i
}

/**
 * Algoritmo de ordenação quick sort usando a mediana de três e 3 partições.
 * @param a array a ordenar
 * *
 * @param left indice a partir do qual é feita a ordenação (inclusivo)
 * *
 * @param right indice até onde é feita a ordenação (inclusivo)
 */
 fun <T> quickSortThreePartition(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T, T)-> Int) {
    var l = left
    var r = right
    var q: IntArray
    while (l < r) {
        // Obter a mediana de três
        a.exchange((l + r).ushr(1), r - 1)
        if (compare(a[r - 1], a[l])<0) a.exchange( r - 1, l)
        if (compare(a[r], a[l]) <0 ) a.exchange( r, l)
        if (compare(a[r], a[r - 1]) <0 ) a.exchange( r, r - 1)
        if (r - l <= 2) return
        // Efectuar as três partições
        q = threePartition(a, l + 1, r - 1, compare)
        // Ordenar parte esquerda e direita. Recursivamente a de menor dimensão.
        if (q[0] - l < r - q[1]) {
            quickSortThreePartition(a, l, q[0], compare)
            l = q[1]
        } else {
            quickSortThreePartition(a, q[1], r, compare)
            r = q[0]
        }
    }
}

/**
 * Parte o array em três partes. Sendo q0 os q1 os indices das partições:
 * a[l .. q0]    < pivot
 * a[q0+1 .. q1-1]   == pivot
 * a[q1 .. r]  > pivot
 * Parte o array em três partes.
 * +----------------------------------------------------------+
 * |  pivot <           |     == pivot     |     > pivot      |
 * +----------------------------------------------------------+
 *                     ^q0                  ^q1
 * @param a array a dividir
 * @param left indice a partir do qual é feita a partição (inclusivo)
 * @param right indice até onde é feita a partição (inclusivo)
 * @return o array com os indices do fim a 1ª partição (último menor do que pivot) e
 *          do início da 3ª partição (primeiro maior do que pivot).
 */
@Suppress("ControlFlowWithEmptyBody")
fun <T> threePartition(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T, T)->Int): IntArray {
     /*
    // Inicialmente parte o array em quatro partes.
    // +----------------------------------------------------------+
    // | == pivot |  pivot <  |    ?      |  > pivot   | == pivot |
    // +----------------------------------------------------------+
    //            ^p          ^i          ^j           ^q
    // Invariante do ciclo
    // 	a[l .. p]       == pivot
    // 	a[p+1 .. i]     <  pivot
    //  a[j-1 .. q]     > pivot
    // 	a[q .. r]       == pivot
    */
    val pivot = a[right]
    var i = left - 1
    var j = right
    var p = left - 1
    var q = right
    // Invariante
    // 	a[l .. p]       == pivot
    // 	a[p+1 .. i]     <  pivot
    //  a[j-1 .. q]     > pivot
    // 	a[q .. r]       == pivot
    while (true) {
        while (compare(a[++i], pivot) <0);
        while ( compare(pivot, a[--j])<0 && j != i);
        if (i >= j) break
        a.exchange(i, j)
        if (compare(a[i], pivot) == 0) a.exchange(i, ++p)
        if (compare(a[j], pivot) == 0)  a.exchange(j, --q)
    }
    // O array está partido em quatro partes.
    // +----------------------------------------------------------+
    // | == pivot |  pivot <         |       > pivot   | == pivot |
    // +----------------------------------------------------------+
    //           ^p                  ^i                ^q
    // Os iguais são colocados no centro.
    var q0 = i - 1; var q1 = i
    for(k in left .. p) a.exchange(k, q0--)
    for( k in right downTo q) a.exchange(k, q1++)
    // O array está partido em três partes.
    // +----------------------------------------------------------+
    // |  pivot <          |      == pivot       |       > pivot  |
    // +----------------------------------------------------------+
    // ^p                  ^q0                    ^q1
    // Os iguais são colocados no centro.
    return intArrayOf(q0, q1)
}

/**
 * Algoritmo de ordenação hibrido
 * Ordenação usando um algoritmo hibrido, insertion sort
 * para array pequenos, e quick sort com mediana de três e
 * com três partições (menores, iguais e maiores) para arrays
 * maiores.
 * @param a array a ordenar
 * @param left indice a partir do qual é feita a partição (inclusivo)
 * @param right indice até onde é feita a partição (inclusivo)
 */
fun <T> quickSortHybrid(a: Array<T>, left: Int=0, right: Int=a.size-1, compare: (T,T)-> Int) {
    var l = left
    var r = right
    while (l < r) {
        // Para sub arrays pequenos usar o insertion sort
        if (r - l < 15) {
           insertionSort(a, l, r, compare)
           return
        }
        // Obter a mediana de três
        a.exchange((l + r).ushr(1), r - 1)
        if (compare(a[r - 1], a[l])<0) a.exchange( r - 1, l)
        if (compare(a[r],a[l]) <0) a.exchange( r, l)
        if (compare(a[r], a[r - 1]) <0 ) a.exchange( r, r - 1)
        // Efectuar as três partições
        val q = threePartition(a, l + 1, r - 1, compare)
        // O array está partido em três partes.
        // +----------------------------------------------------------+
        // |  pivot <          |      == pivot       |       > pivot  |
        // +----------------------------------------------------------+
        //                   ^q0                      ^q1
        if (q[0] - l < r - q[1]) {
            quickSortHybrid(a, l, q[0], compare)
            l = q[1]
        } else {
            quickSortHybrid(a, q[1], r, compare)
            r = q[0]
        }
    }
}

