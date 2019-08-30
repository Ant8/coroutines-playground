package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.*
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

    private fun printHelloAndThreadId() {
        println(Thread.currentThread().id)
        println("hello, ")
        Thread.sleep(5000)
    }

    private fun printThreadId(message: String = "world!") {
        println(message)
        println(Thread.currentThread().id)
    }
}