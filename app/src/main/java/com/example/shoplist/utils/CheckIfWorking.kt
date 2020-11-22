package com.example.shoplist.utils

import com.example.shoplist.model.DateCalculator
import com.example.shoplist.model.WorkingHours
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by sopovardidze
 */
object CheckIfWorking {

    val weekDaysGE = listOf(
        "ორშაბათი",
        "სამშაბათი",
        "ოთხშაბათი",
        "ხუთშაბათი",
        "პარასკევი",
        "შაბათი",
        "კვირა"
    )

    private fun calculateDate(hours: List<WorkingHours>): DateCalculator {
        val current = Calendar.getInstance()
        val day = current.get(Calendar.DAY_OF_WEEK)

        val today = hours[day - 1]
        val currentDay: String = SimpleDateFormat("dd-M-yyyy", Locale.getDefault()).format(Date())
        val dateFrom = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("$currentDay ${today.from}")
        val dateTo = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("$currentDay ${today.to}")

        val currentTime = System.currentTimeMillis()

        return DateCalculator(day, today, dateFrom, dateTo, currentTime)
    }


    fun checkWorkingHours(workingHours: List<WorkingHours>): Boolean {
        val (dayInWeek, today, dateFrom, dateTo, currentTime) = calculateDate(workingHours)

        return currentTime > dateFrom.time && currentTime < dateTo.time && today.working
    }

    fun checkNextWorkingDay(workingHours: List<WorkingHours>): String {
        val (dayInWeek, today, dateFrom, dateTo, currentTime) = calculateDate(workingHours)

        if (currentTime < dateFrom.time) {
            return "${weekDaysGE[dayInWeek - 1]}, ${today.from} - ${today.to}"
        }

        if (currentTime > dateTo.time) {
            for (day in dayInWeek + 1..dayInWeek + 6) {
                val tomorrowIndex = (day + 1) % 7
                val isWorkingDay = workingHours[tomorrowIndex]
                if (isWorkingDay.working) {
                    return "${weekDaysGE[dayInWeek - 1]}, ${isWorkingDay.from} - ${isWorkingDay.to}"
                }
            }
        }

        return ""
    }
}