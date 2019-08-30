package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.*
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Composing {
    @ExperimentalTime
    @Test
    fun runSequentially() = runBlocking {
        val timedValue = measureTimedValue {
            doSomethingUsefulOne() + doSomethingUsefulTwo()
        }
        println("completion time ${timedValue.duration}, result ${timedValue.value}")
    }

    @ExperimentalTime
    @Test
    fun runInParallel() = runBlocking {
        val timedValue = measureTimedValue {
            val one = async {
                doSomethingUsefulOne()
            }
            val two = async {
                doSomethingUsefulTwo()
            }
            one.await() + two.await()
        }
        println("completion time ${timedValue.duration}, result ${timedValue.value}")
    }

    @ExperimentalTime
    @Test
    fun startLazy() = runBlocking {
        val timedValue = measureTimedValue {
            val one = async(start = CoroutineStart.LAZY) {
                doSomethingUsefulOne()
            }
            val two = async(start = CoroutineStart.LAZY) {
                doSomethingUsefulTwo()
            }
            one.start()
            two.start()
            one.await()
        }
        println("completion time ${timedValue.duration}, result ${timedValue.value}")
    }

    @ExperimentalTime
    @Test
    fun startDefault() = runBlocking {
        val timedValue = measureTimedValue {
            val one = async(start = CoroutineStart.DEFAULT) {
                doSomethingUsefulOne()
            }
            val two = async(start = CoroutineStart.DEFAULT) {
                doSomethingUsefulTwo()
            }
            one.start()
            two.start()
            one.await()
        }
        println("completion time ${timedValue.duration}, result ${timedValue.value}")
    }

    @ExperimentalTime
    @Test
    fun discouragedAsyncStyle() {
        val timedValue = measureTimedValue {
            val one = doSomethingUsefulOneAsync()
            val two = doSomethingUsefulTwoAsync()

            runBlocking {
                println("result ${one.await() + two.await()}")
            }
        }
        println("completion time ${timedValue.duration}")
    }

    @ExperimentalTime
    @Test
    fun structuredConcurrency() = runBlocking {
        val timedValue = measureTimedValue {
            var i = 0
            try {
                withTimeout(100) {
                    i = concurrentSum()
                }
            } catch (e: Exception) {
                print(e)
            }
            return@measureTimedValue i
        }
        println("${timedValue.duration}, ${timedValue.value}")
    }

    private suspend fun concurrentSum(): Int = coroutineScope {
        val one = async {
            doSomethingUsefulOne()
        }
        val two = async {
            doSomethingUsefulTwo()
        }
        one.await() + two.await()
    }

    private fun doSomethingUsefulOneAsync(): Deferred<Int> = GlobalScope.async {
        doSomethingUsefulOne()
    }

    private fun doSomethingUsefulTwoAsync(): Deferred<Int> = GlobalScope.async {
        doSomethingUsefulTwo()
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1500L) // pretend we are doing something useful here, too
        return 29
    }
}