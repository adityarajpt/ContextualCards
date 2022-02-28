package com.rajaditya.fampayassignment.utils

import android.content.SharedPreferences

fun SharedPreferences.Editor.putIntArray(key: String, value: IntArray): SharedPreferences.Editor {
    return putString(key, value.joinToString(
        separator = ",",
        transform = { it.toString() }))
}

fun SharedPreferences.getIntArray(key: String): IntArray {
    with(getString(key, "")) {
        with(if(this?.isNotEmpty() == true) this.split(',') else return intArrayOf()) {
            return IntArray(count()) { this[it].toInt() }
        }
    }
}