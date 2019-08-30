package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.*
import org.junit.Test

class TimeoutsAndCancellation {

    @Test
    fun cancellingCoroutineExecution() = runBlocking {
        val zajob = launch {
            repeat(100) { i ->
                println("zajob: $i")
                delay(100)
            }
        }
        delay(1000)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }

    @Test
    fun cancellingNonCooperatingCoroutine() = runBlocking {
        val startTime = System.currentTimeMillis()
        val zajob = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 15) {
               if (System.currentTimeMillis() >= nextPrintTime) {
                   println("job: i'm sleeping ${i++}")
                   nextPrintTime += 100L
               }
            }
        }

        delay(1000)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }

    @Test
    fun cancellingCooperatingCoroutine() = runBlocking {
        val startTime = System.currentTimeMillis()
        val zajob = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive and (i < 15)) {
               if (System.currentTimeMillis() >= nextPrintTime) {
                   println("job: i'm sleeping ${i++}")
                   nextPrintTime += 100L
               }
            }
        }

        delay(1000)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }

    @Test
    fun cancellingCooperatingCoroutineWithTry() = runBlocking {
        val zajob = launch(Dispatchers.Default) {
            try {
                printAndWait()
            } finally {
                println("job: i'm finalising")
            }
        }

        delay(1000)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }

    @Test
    fun cancellingCooperatingCoroutineWithTryAndNonCancellableFinally() = runBlocking {
        val zajob = launch(Dispatchers.Default) {
            try {
                printAndWait()
            } finally {
                withContext(NonCancellable) {
                    println("job: i'm finalising")
                    delay(1000)
                    println("job: i've just waited for 1 s, because I'm non-cancellable")
                }
            }
        }

        delay(1000)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }

    @Test
    fun cancellingCooperatingCoroutineWithoutTry() = runBlocking {
        val zajob = launch(Dispatchers.Default) {
            printAndWait()
        }
//this does not crash nor visibly throw an exception
        delay(1000)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }

    private suspend fun printAndWait() {
        repeat(1000) { i ->
            println("job: i'm sleeping $i")
            delay(100)
        }
    }

    @Test
    fun cancellingNonCooperatingCoroutineWithContext() = runBlocking {
        val zajob = withContext(NonCancellable) {
            launch {
                printAndWait()
            }
        }
//this does not crash nor visibly throw an exception
        delay(10)
        println("main: tired of waitin'!")
        zajob.cancelAndJoin()
        println("main: I can quit now")
    }


    @Test
    fun timeout() = runBlocking {
        withTimeout(600) {
            printAndWait()
        }
    }

    @Test
    fun timeoutWithoutException() = runBlocking {
        try {
            withTimeout(600) {
                printAndWait()
            }
        } catch (e: Exception) {
            println("exception was $e")
        }
    }

    @Test
    fun timeoutWithoutExceptionByNull() = runBlocking {
        val result = withTimeoutOrNull(600) {
            printAndWait()
        }
        println("$result is the result")
    }
}