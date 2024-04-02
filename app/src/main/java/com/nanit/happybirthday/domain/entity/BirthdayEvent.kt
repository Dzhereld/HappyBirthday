package com.nanit.happybirthday.domain.entity

data class BirthdayEvent(
    val name: String,
    val ageInMonths: Int,
    val theme: ThemeType
)