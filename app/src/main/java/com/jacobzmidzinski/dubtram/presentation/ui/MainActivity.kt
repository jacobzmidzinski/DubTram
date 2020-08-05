package com.jacobzmidzinski.dubtram.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val tramsTramsForecastFragment = TramsForecastFragment()
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, tramsTramsForecastFragment)
                .commit()
        }
    }
}