package com.coc00031.assignment

import android.os.Bundle
import android.preference.PreferenceFragment
import android.support.design.widget.Snackbar
import kotlin.system.exitProcess

class Fragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.night_day_theme)
        findPreference("night_day_theme").setOnPreferenceChangeListener { _, _ ->
            Snackbar.make(view, R.string.restart_message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.restart) { exitProcess(0) }.show()
            true
        }
    }
}