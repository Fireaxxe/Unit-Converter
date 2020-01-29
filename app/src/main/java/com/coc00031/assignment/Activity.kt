package com.coc00031.assignment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.setContentView

class Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SettingsUI().setContentView(this)
        fragmentManager.beginTransaction().replace(R.id.container, Fragment()).commit()
    }
}