package com.nanit.happybirthday.domain.entity

import java.util.Date

data class ChildProfile(
    val name: String,
    val dob: Date,
    val theme: String
)