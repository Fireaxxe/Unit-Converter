package com.coc00031.assignment

object Conversions {
    /**
     * This class adds the below units to a mutableList (similar to ArrayList) and it sends the values to UnitCalc for conversion
     */
    private val units_conversion = mutableMapOf<Units, List<UnitCalc>>()
    private fun add(item: Units, init: MutableList<UnitCalc>.() -> Unit) {
        mutableListOf<UnitCalc>().apply(init).let { units_conversion += item to it }
    }

    private fun MutableList<UnitCalc>.unit(name: Int, symbol: Int, a: Double = 1.0, b: Double = 0.0, n: Double = 1.0) {
        this += UnitConversion(name, symbol, a, b, n)
    }

    fun retrieveUnits(item: Units) = units_conversion[item]!!

    init {
        add(Units.AREA) {
            unit(R.string.sq_metre, R.string.sq_metre_symbol)
            unit(R.string.sq_kilometre, R.string.sq_kilometre_symbol, 1e6)
            unit(R.string.sq_yard, R.string.sq_yard_symbol, 0.83612736)
            unit(R.string.sq_foot, R.string.sq_foot_symbol, 9.290304e-2)
            unit(R.string.sq_inch, R.string.sq_inch_symbol, 6.4516e-4)
            unit(R.string.sq_mile, R.string.sq_mile_symbol, 2.59e+6)
        }

        add(Units.LENGTH) {
            unit(R.string.mile, R.string.mile_symbol, 1609.344)
            unit(R.string.micrometre, R.string.micrometre_symbol, 1e-6)
            unit(R.string.nanometre, R.string.nanometre_symbol, 1e-9)
            unit(R.string.millimetre, R.string.millimetre_symbol, 0.001)
            unit(R.string.centimetre, R.string.centimetre_symbol, 0.01)
            unit(R.string.metre, R.string.metre_symbol)
            unit(R.string.kilometre, R.string.kilometre_symbol, 1000.0)
            unit(R.string.inch, R.string.inch_symbol, 0.0254)
            unit(R.string.light_year, R.string.light_year_symbol, 9.4607304725808e15)
            unit(R.string.yard, R.string.yard_symbol, 0.9144)
            unit(R.string.feet, R.string.foot_symbol, 0.3048)
            unit(R.string.nautical_mile, R.string.nautical_mile_symbol, 1852.0)
        }

        add(Units.VOLUME) {
            unit(R.string.cu_metre, R.string.cu_metre_symbol)
            unit(R.string.litre, R.string.litre_symbol, 0.001)
            unit(R.string.millilitre, R.string.millilitre_symbol, 1e-6)
            unit(R.string.cu_foot, R.string.cu_foot_symbol, 0.028316846592)
            unit(R.string.cu_inch, R.string.cu_inch_symbol, 16.387064e-6)
            unit(R.string.gallon, R.string.gallon_symbol, 4.54609e-3)
            unit(R.string.quart, R.string.quart_symbol, 1.1365225e-3)
            unit(R.string.pint, R.string.pint_symbol, 568.26125e-6)
            unit(R.string.fluid_ounce, R.string.fluid_ounce_symbol, 28.4130625e-6)
            unit(R.string.tablespoon, R.string.tablespoon_symbol, 17.7581640625e-6)
            unit(R.string.teaspoon, R.string.teaspoon_symbol, 5.9193880208333e-6)
        }

        add(Units.MASS) {
            unit(R.string.kilogram, R.string.kilogram_symbol)
            unit(R.string.stone, R.string.stone_symbol, 6.35029318)
            unit(R.string.pound, R.string.pound_symbol, 0.45359237)
            unit(R.string.ounce, R.string.ounce_symbol, 28.349523125e-3)
            unit(R.string.tonne, R.string.tonne_symbol, 1000.0)
            unit(R.string.gram, R.string.gram_symbol, 0.001)
        }

        add(Units.TIME) {
            unit(R.string.millisecond, R.string.millisecond_symbol, 0.001)
            unit(R.string.second, R.string.second_symbol)
            unit(R.string.minute, R.string.minute_symbol, 60.0)
            unit(R.string.hour, R.string.hour_symbol, 3.6e3)
            unit(R.string.day, R.string.day_symbol, 86.4e3)
            unit(R.string.week, R.string.week_symbol, 604.8e3)
            unit(R.string.month, R.string.month_symbol, 2.592e6)
            unit(R.string.year, R.string.year_symbol, 31.556952e6)
            unit(R.string.decade, R.string.decade_symbol, 315.56952e6)
            unit(R.string.century, R.string.century_symbol, 3.1556952e9)
        }

        add(Units.SPEED) {
            unit(R.string.metre_sec, R.string.metre_sec_symbol)
            unit(R.string.mile_h, R.string.mile_h_symbol, 0.44704)
            unit(R.string.km_h, R.string.km_h_symbol, 0.277778)
            unit(R.string.knot, R.string.knot_symbol, 0.514444855556)
            unit(R.string.s_light, R.string.s_light_symbol, 299792458.0)
            unit(R.string.s_sound, R.string.s_sound_symbol, 343.2)
        }

        add(Units.TEMPERATURE) {
            unit(R.string.kelvin, R.string.kelvin_symbol)
            unit(R.string.celsius, R.string.celsius_symbol, 1.0, 273.15)
            unit(R.string.fahrenheit, R.string.fahrenheit_symbol, 5.0 / 9, 459.67 * 5 / 9)
        }

        add(Units.DIGITAL_STORAGE) {
            unit(R.string.bit, R.string.bit_symbol)
            unit(R.string.byte_, R.string.byte_symbol, 8.0)
            unit(R.string.kbit, R.string.kbit_symbol, 1000.0)
            unit(R.string.kbyte, R.string.kbyte_symbol, 8000.0)
            unit(R.string.mbit, R.string.mbit_symbol, 1e6)
            unit(R.string.mbyte, R.string.mbyte_symbol, 8e6)
            unit(R.string.gbit, R.string.gbit_symbol, 1e9)
            unit(R.string.gbyte, R.string.gbyte_symbol, 8e9)
            unit(R.string.tbit, R.string.tbit_symbol, 1e12)
            unit(R.string.tbyte, R.string.tbyte_symbol, 8e12)
            unit(R.string.pbit, R.string.pbit_symbol, 1e15)
            unit(R.string.pbyte, R.string.pbyte_symbol, 8e15)
        }

        add(Units.DATA_TRANSFER_RATE) {
            unit(R.string.kbps, R.string.kbps_symbol, 1000.0)
            unit(R.string.kB_s, R.string.kB_s_symbol, 8000.0)
            unit(R.string.Mbps, R.string.Mbps_symbol, 1e6)
            unit(R.string.MB_s, R.string.MB_s_symbol, 8e6)
            unit(R.string.Gbps, R.string.Gbps_symbol, 1e9)
            unit(R.string.GB_s, R.string.GB_s_symbol, 8e9)
            unit(R.string.Tbps, R.string.Tbps_symbol, 1e12)
            unit(R.string.TB_s, R.string.TB_s_symbol, 8e12)
        }
    }
}