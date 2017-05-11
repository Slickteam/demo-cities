package Maths

import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * Created by jguidoux on 07/05/2017.
 */


fun fibo_imp(n: Long): BigInteger {
    var a = BigInteger.ZERO
    var b = BigInteger.ONE
    for (i in 1..n) {
        val t = a + b
        a = b
        b = t
    }

    return a

}


fun fibo_fun(n: Int): Long =
        when (n) {
            0 -> 0
             1 -> 1
            else -> fibo_fun(n - 1) + fibo_fun(n - 2)
        }

// Unfortunately tailrec not work in this case
tailrec fun fibo_fun_opt(n: Int): Long =
        when (n) {
            0 -> 0
            1 -> 1
            else -> fibo_fun_opt(n - 1) + fibo_fun_opt(n - 2)
        }


 fun fibo_fun_opt2(n : Long) : BigInteger {
    tailrec fun loopFibo(index: Long, previous: BigInteger = BigInteger.ZERO, actual: BigInteger = BigInteger.ONE): BigInteger =
            when (index) {
                0L -> previous
                else -> loopFibo(index - 1, actual, previous + actual)
            }
    return loopFibo(n)
}

fun main(args: Array<String>) {
    meseureTimeFibo(5)
    meseureTimeFibo(10)
    meseureTimeFibo(15)
    meseureTimeFibo(20)
    meseureTimeFibo(100)
    meseureTimeFibo(1000)
    meseureTimeFibo(10000)
//    meseureTimeFibo(50)
//    meseureTimeFibo(50)


}

private fun meseureTimeFibo(value: Long) {

    println()
    println("test with $value")
    mesureTime1Fibo(value, ::fibo_imp.name) {fibo_imp(value)}
//    mesureTime1Fibo(value, {fibo_fun(value)})
//    mesureTime1Fibo(value, {fibo_fun_opt(value)})
    mesureTime1Fibo(value, ::fibo_fun_opt2.name) {fibo_fun_opt2(value)}

//    println(measureTimeMillis { fibo_fun(55) })
//    println(measureTimeMillis { fibo_fun_opt(55) })
    println()
}

private fun mesureTime1Fibo(value: Long, funName: String, fibo : (value: Long) -> BigInteger) {
   val  time = measureTimeMillis {
       val result = fibo(value)
       print("function $funName : result = $result " )
   }
    println(", calculate in $time ms")
}