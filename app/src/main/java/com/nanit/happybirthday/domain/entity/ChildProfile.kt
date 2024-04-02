package com.nanit.happybirthday.domain.entity

data class ChildProfile(
    val name: String,
    val ageInMonths: Int,
    val theme: ThemeType
)