package com.coc00031.assignment

import android.content.Context
import kotlin.math.pow

/**
 * Convert from unit to base and back: f(x) = [a] x^[n] + [b].
 */
data class UnitConversion(override val label: Int, override val shortLabel: Int, val a: Double, val b: Double, val n: Double) : UnitCalc {

    override fun selfToBase(context: Context, x: Double) = (a * x.pow(n) + b)
    override fun baseToSelf(context: Context, y: Double) = ((y - b) / a).pow(1 / n)
}



