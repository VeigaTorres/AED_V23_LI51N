package week7ADT

import week7ADT.queue.LinkedQueue
import week7ADT.queue.Queue
import week7ADT.stack.ArrayStack
import week7ADT.stack.LinkedStack
import week7ADT.stack.Stack
fun print( values: Iterable<Int> ) {
    for(v in values) print( "$v " )
    //values.forEach { print("$it") }
    println()
    println("first - ${values.first()}")
    println("last - ${values.last()}")
    println("number of even - ${values.count { it%2 == 0 } }")
    println("even numbers - ${values.filter { it%2 == 0 } }")
    println("toString - ${values}")
}

fun <E> Queue<E>.fill(vararg values: E): Queue<E>{
    values.forEach { offer( it ) }
    return this
}

fun <E> Stack<E>.fill( vararg values: E): Stack<E>{
    values.forEach { push( it ) }
    return this
}
fun main(args: Array<String>) {
    print(ArrayStack<Int>(10).fill( 1, 2, 3, 4 ,5))
    println("^ ArrayStack-----")
    print(LinkedStack<Int>().fill( 1, 2, 3, 4 ,5))
    println("^ LinkedStack-----")
    print(LinkedQueue<Int>().fill( 1, 2, 3, 4 ,5))
    println("^ LinkedQueue-----")
    print( listOf(1,3, 4) )
    println("^ listOf-----")
}