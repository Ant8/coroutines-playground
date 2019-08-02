package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.*
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CoroutinesExamplesTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun coroutinesTestWithRunBlocking() {
        println(DateTime.now())

        runBlocking {
            delay(2000)
        }

        println(DateTime.now())
    }

    @Test
    fun coroutinesTestWithRunBlockingRevisited() =
        runBlocking {
            println(DateTime.now())
            delay(2000)
            println(DateTime.now())
        }

    @Test
    fun coroutinesTestWithLaunch() {
        GlobalScope.launch {
            delay(1000)
            println("World!")
        }
        println("Helou, ")
    }

    @Test
    fun coroutinesTestWithLaunchRevisited() =
        runBlocking {
            launch {
                delay(1000)
                println("World!")
            }
            println("Helou, ")
        }

    @Test
    fun testWithDeferredAwait() {
        val deferred: Deferred<String> = GlobalScope.async {
            delay(1000L)
            "World!"
        }

        runBlocking {
            println("Helou, ${deferred.await()}")
        }
    }

    @Test
    fun testWithDeferredAwaitRevisited() =
        runBlocking {
            val deferred: Deferred<String> =
                async {
                    delay(1000)
                    "World!"
                }

            println("Helou, ${deferred.await()}")
        }
}
