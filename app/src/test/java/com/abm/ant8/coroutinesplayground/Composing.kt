package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }
}