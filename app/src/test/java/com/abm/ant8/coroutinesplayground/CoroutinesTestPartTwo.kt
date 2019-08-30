package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.*
import org.junit.Ignore
import org.junit.Test
import kotlin.concurrent.thread

class CoroutinesTestPartTwo {

    @Test
    fun basicCoroutine() {
        GlobalScope.launch {
            delay(200)
            printThreadId()
        }
        printHelloAndThreadId()
    }

    @Test
    fun basicCoroutineWithThread() {
        thread {
            Thread.sleep(200)
            printThreadId()
        }
        printHelloAndThreadId()
    }

    @Test
    fun twoParallelCoroutines() {
        GlobalScope.launch {
            val deferred = async {
                delay(100)
                printHelloAndThreadId()
            }
            val deferred2 = async {
                delay(100)
                printThreadId()
            }
            deferred2.await()
            deferred.await()
        }
        Thread.sleep(1000)
    }

    @Test
    fun testAwait() = runBlocking {
        val job = GlobalScope.launch {
            delay(100)
            printThreadId()
        }
        printHelloAndThreadId()
        job.join()
        println("hehehe")
    }

    @Test
    fun structuredConcurency() = runBlocking {
        launch {
            delay(200)
            printThreadId()
        }
        printHelloAndThreadId()
    }

    @Test
    fun runTwoParallelAsStructuredConcurency() = runBlocking {
        val deferred1 = async {
            delay(200)
            printThreadId()
        }
        val deferred2 = async {
            printHelloAndThreadId()
        }
        deferred1.await()
        deferred2.await()
    }

    @Test
    fun scopeBuilderTest() = runBlocking {
        launch {
            delay(700)
            printThreadId("from launch")
        }

        coroutineScope {
            launch {
                delay(100)
                printThreadId("from nested launch")
            }
            delay(400)
            printThreadId("from coroutine scope")
        }
        printThreadId("coroutine scope is over")
    }

    @Test
    fun coroutinesARElightweightTest() = runBlocking {
        repeat(100_000) {
            launch {
                delay(200)
                print(".")
            }
        }
    }

    @Ignore("this crashes, will probably be fixed")
    @Test
    fun threadsAREnotThatLightweigt() {
        (1..100_000)
            .asSequence()
            .map {
                thread {
                    Thread.sleep(200)
                    print(".")
                }
            }
            .forEach { it.start() }
    }

    @Test
    fun useSuspendFun() = runBlocking {
        launch {
            firstSuspendedFun()
        }
        printHelloAndThreadId()
    }

    @Test
    fun coroutinesFromGlobalScopeShutDown() = runBlocking {
        GlobalScope.launch {
            var i = 0
            repeat(100) {
                println("${i++}")
                delay(100)
            }
        }
        delay(1500)
    }

    private fun printHelloAndThreadId() {
        println(Thread.currentThread().id)
        println("hello, ")
        Thread.sleep(1000)
    }

    private fun printThreadId(message: String = "world!") {
        println(message)
        println(Thread.currentThread().id)
    }

    private suspend fun firstSuspendedFun() {
        delay(200)
        printThreadId()
    }
}