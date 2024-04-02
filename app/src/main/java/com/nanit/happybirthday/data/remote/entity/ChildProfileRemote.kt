package com.nanit.happybirthday.data.remote.entity

import com.nanit.happybirthday.data.remote.util.DateSerializer
import com.nanit.happybirthday.domain.entity.ChildProfile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ChildProfileRemote(
    @SerialName("name") val name: String,

    @Serializable(with = DateSerializer::class)
    @SerialName("dob") val dob: Date,

    @SerialName("theme") val theme: String
)

fun ChildProfileRemote.mapToChildProfile() = ChildProfile(
    name = name,
    dob = dob,
    theme = theme
)