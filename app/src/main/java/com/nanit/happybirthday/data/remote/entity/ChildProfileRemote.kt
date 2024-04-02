package com.nanit.happybirthday.data.remote.entity

import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ThemeType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ChildProfileRemote(
    @SerialName("name") val name: String,
    @SerialName("dob") val dob: Long,
    @SerialName("theme") val theme: String
)

fun ChildProfileRemote.mapToChildProfile() = ChildProfile(
    name = name,
    dob = Date(dob),
    theme = when (theme) {
        "pelican" -> ThemeType.PELICAN
        "fox" -> ThemeType.FOX
        "elephant" -> ThemeType.ELEPHANT
        else -> ThemeType.UNDEFINED
    }
)