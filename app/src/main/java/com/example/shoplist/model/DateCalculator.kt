package com.example.shoplist.model

import java.util.*

/**
 * Created by sopovardidze
 */
data class DateCalculator(
    val dayInWeek : Int,
    val today : WorkingHours,
    val dateFrom : Date,
    val dateTo : Date,
    val currentTime : Long
) {
}