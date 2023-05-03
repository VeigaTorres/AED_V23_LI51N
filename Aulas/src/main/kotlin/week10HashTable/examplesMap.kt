package week10HashTable


import java.util.TreeMap
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

fun main(args: Array<String>){
    val m= mapOf("um" to 1, "dois" to 2, "três" to 3)
    //m.put("cinco", 5) // ERROR imutavel
    println("mapOf -> s = $m")
    val m1= mutableMapOf("um" to 1, "dois" to 2, "três" to 3)
    m1.put("cinco", 5)
    println("mutableMapOf -> m + (\"cinco\", 5) = $m1")
    val m2= hashMapOf("um" to 1, "dois" to 2, "três" to 3)
    m2.put("cinco", 5)
    println("hashMaptOf -> m + (\"cinco\", 5) = $m2")
    val m3= linkedMapOf("um" to 1, "dois" to 2, "três" to 3)
    m3.put("cinco", 5)
    println("linkedMapOf -> m + (\"cinco\", 5) = $m3")
    val m4= sortedMapOf("um" to 1, "dois" to 2, "três" to 3)
    m4.put("cinco", 5)
    println("sortedMapOf ->m + (\"cinco\", 5) = $m4")

    println("HashMap(m1) ->  ${HashMap(m1)}")
    println("LinkedHashMap(m1) ->  ${LinkedHashMap(m1)}")
    println("TreeMap(m1) ->  ${TreeMap(m1)}")
}
