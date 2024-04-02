package com.nanit.happybirthday.data.remote.entity

import com.nanit.happybirthday.domain.entity.BirthdayEvent
import com.nanit.happybirthday.domain.entity.ThemeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.joda.time.DateTime
import org.joda.time.Months


@Serializable
data class BirthdayEventRemote(
    @SerialName("name") val name: String,
    @SerialName("dob") val dob: Long,
    @SerialName("theme") val theme: String
)

fun BirthdayEventRemote.mapToBirthdayEvent() = BirthdayEvent(
    name = name,
    ageInMonths = dob.millisecondsToMonthFromNow(),
    theme = when (theme) {
        "pelican" -> ThemeType.PELICAN
        "fox" -> ThemeType.FOX
        "elephant" -> ThemeType.ELEPHANT
        else -> ThemeType.UNDEFINED
    }
)

/**
 * Calculates the number of months between the provided epoch time and the current time.
 * If the calculated number of months is 0 or less, returns 1.
 *
 * @param epochTime The epoch time in milliseconds to calculate the number of months from.
 * @return The number of months between the provided epoch time and the current time.
 */
private fun Long.millisecondsToMonthFromNow(): Int {
    val birthday = DateTime(this)
    val currentDate = DateTime.now()

    val monthsDifference = Months.monthsBetween(birthday, currentDate).months

    return monthsDifference.takeIf { it > 0 } ?: 1
}
