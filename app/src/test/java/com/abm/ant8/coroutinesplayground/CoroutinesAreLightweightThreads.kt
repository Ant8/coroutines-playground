package com.abm.ant8.coroutinesplayground

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.concurrent.thread

class CoroutinesAreLightweightThreads {

    @Test
    fun hundredThousandDotsCoroutines() {
        runBlocking {
            (1..INSTANCES_COUNT).forEach {
                launch {
                    print(".")
                }
            }
        }
    }

    @Test
    fun hundredThousandDotsThreads() {
        (1..INSTANCES_COUNT).forEach {
            thread {
                print(".")
            }
        }
    }

    @Test
    fun hundredThousandDotsThreadsFast() {
        (1..INSTANCES_COUNT).forEach {
            Thread().run {
                print(".")
            }
        }
    }

    private companion object {
        const val INSTANCES_COUNT = 1_000_000
    }
}

//