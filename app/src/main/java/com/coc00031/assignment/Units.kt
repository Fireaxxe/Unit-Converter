package com.coc00031.assignment

enum class Units(val id: Int) {

    LENGTH(id = R.id.unit_length),
    AREA(id = R.id.unit_area),
    VOLUME(id = R.id.unit_volume),
    MASS(id = R.id.unit_mass),
    TIME(id = R.id.unit_time),
    SPEED(id = R.id.unit_speed),
    TEMPERATURE(id = R.id.unit_temperature),
    DIGITAL_STORAGE(id = R.id.unit_digital_storage),
    DATA_TRANSFER_RATE(id = R.id.data_transfer_rate);

    companion object Utils {
        fun isIdSafe(id: Int) = get(id) != null
        fun get(id: Int?) = values().firstOrNull { it.id == id }
    }
}