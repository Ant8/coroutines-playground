package com.abm.ant8.coroutinesplayground.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abm.ant8.coroutinesplayground.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.root, SeasonListFragment.newInstance())
            .commit()
    }
}
