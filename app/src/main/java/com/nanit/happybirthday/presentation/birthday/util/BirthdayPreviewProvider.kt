package com.nanit.happybirthday.presentation.birthday.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nanit.happybirthday.domain.entity.BirthdayEvent
import com.nanit.happybirthday.domain.entity.ThemeType

class BirthdayPreviewProvider : PreviewParameterProvider<BirthdayEvent> {
    override val values: Sequence<BirthdayEvent> = sequenceOf(
        BirthdayEvent("John", 6, ThemeType.PELICAN),
        BirthdayEvent("Alice", 12, ThemeType.FOX),
        BirthdayEvent("Bob", 60, ThemeType.ELEPHANT)
    )
}
