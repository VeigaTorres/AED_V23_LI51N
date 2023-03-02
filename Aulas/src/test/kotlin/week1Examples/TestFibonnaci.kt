package week1Examples

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestFibonnaci {

    val fibonacci:(Int)-> Int = ::fibonacci
    @Test
    fun testFirsts(){
        assertEquals(0, fibonacci(0))
        assertEquals(1, fibonacci(1))
        assertEquals(1, fibonacci(2))
    }
    @Test
    fun testSmallValues(){
        assertEquals( 377, fibonacci(14))
        assertEquals( 4181, fibonacci(19))
    }

    @Test
    fun test(){
        fibonacci( 50000 )
    }
}