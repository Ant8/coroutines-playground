package com.abm.ant8.coroutinesplayground

import androidx.test.runner.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class WrcResultsRepoInstrumentedPseudoTest {

    @Test
    fun baseChampionshipResultsTest() =
        runBlocking {
            println(WrcResultsRepository.getTopTenDriversOverallFor(2015))
        }

    @Test
    fun baseRallyResultsTest() =
        runBlocking {
            println(WrcResultsRepository.getTopTenCrewsFor("monte", 2015))
        }

    @Test
    fun rallyResultsErrorTest() =
        runBlocking {
            try {
                println(WrcResultsRepository.getTopTenCrewsFor("poland", 2019))
            } catch (exception: Exception) {
                println("exception $exception")
            }
        }

    @Test
    fun getRalliesForSeasonTest() =
        println(WrcResultsRepository.getRalliesFor(2017))
}
