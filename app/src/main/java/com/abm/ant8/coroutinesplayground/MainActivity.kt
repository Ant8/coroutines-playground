package com.abm.ant8.coroutinesplayground

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import biz.kasual.materialnumberpicker.MaterialNumberPicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var driverChampionshipsStandings = DriverChampionshipsStandings(emptyList())

    private val numberPicker by lazy {
        MaterialNumberPicker.Builder(this)
            .minValue(firstSeason)
            .maxValue(lastSeason)
            .defaultValue(lastSeason)
            .textSize(20f)
            .build()
    }

    private val dialog by lazy {
        AlertDialog.Builder(this)
            .setTitle("select WRC season")
            .setView(numberPicker)
            .setPositiveButton("OK") { _, _ -> onSeasonSelected(numberPicker.value) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    private fun onSeasonSelected(season: Int) {
        progressBar.visibility = VISIBLE
        selectSeasonButton.text = season.toString()
        GlobalScope.launch(Dispatchers.Main) {
            driverChampionshipsStandings = withContext(Dispatchers.IO) {
                WrcResultsRepository.getTopTenDriversOverallFor(season)
            }
            println(driverChampionshipsStandings)
            progressBar.visibility = GONE
        }
    }

    private fun setupViews() {
        selectSeasonButton.apply {
            text = lastSeason.toString()
            setOnClickListener {
                dialog.show()
            }
        }
    }
}
