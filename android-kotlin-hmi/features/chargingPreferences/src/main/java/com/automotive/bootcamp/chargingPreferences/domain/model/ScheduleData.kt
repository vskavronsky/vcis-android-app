package com.automotive.bootcamp.chargingPreferences.domain.model

import java.time.DayOfWeek
import java.time.LocalTime

class ScheduleData(val day: Set<DayOfWeek>, val startTime: LocalTime, val endTime: LocalTime, val enabled: Boolean ) {
}

object ScheduleValues {
    private val WeekDay = setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)
    private val WeekEnd = setOf(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    private val sWeekDay = setOf(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY)
    private val sWeekEnd = setOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)

    fun weekDay(normal : Boolean = true) : Set<DayOfWeek> {
        if(normal) return  WeekDay
        return sWeekDay
    }
    fun weekEnd(normal : Boolean = true) : Set<DayOfWeek> {
        if(normal) return  WeekEnd
        return sWeekEnd
    }
}