package com.coc00031.assignment

import android.content.Context

interface UnitCalc {

    val label: Int //unit name
    val shortLabel: Int //unit symbol
    fun selfToBase(context: Context, x: Double): Double //convert X to base
    fun baseToSelf(context: Context, y: Double): Double // convert Y to base
}