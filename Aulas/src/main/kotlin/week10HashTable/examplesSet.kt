package week10HashTable


import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet
import java.util.TreeSet

fun main(args: Array<String>){
    val s= setOf(1, 7, 2, 80, 81, 0, 160)
    //s.add(4)  // ERROr imutavel
    println("setOf -> s = $s")
    val s1= mutableSetOf(1, 7, 2, 80, 81, 0, 160)
    s1.add(4)
    println("mutableSetOf -> s + 4 = $s1")
    val s2= hashSetOf(1, 7, 2, 80, 81, 0, 160)
    s2+= 4
    println("hashSetOf -> s + 4 = $s2")
    s2.add(4)
    val s3= linkedSetOf(1, 7, 2, 80, 81, 0, 160)
    s3.add(4)
    println("linkedSetOf -> s + 4 = $s3")

    val s4= sortedSetOf(1, 7, 2, 80, 81, 0, 160)
    s4.add(4 )
    println("sortedSetOf -> s + 4 = $s4")

    println("HashSet(s) ->  ${HashSet(s)}")
    println("LinkedHashSet(s) ->  ${LinkedHashSet(s)}")
    println("TreeSet(s) ->  ${TreeSet(s)}")
}
