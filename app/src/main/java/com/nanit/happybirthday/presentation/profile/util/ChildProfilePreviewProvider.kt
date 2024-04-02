package com.nanit.happybirthday.presentation.profile.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ThemeType

class ChildProfilePreviewProvider : PreviewParameterProvider<ChildProfile> {
    override val values: Sequence<ChildProfile> = sequenceOf(
        ChildProfile("John", 6, ThemeType.PELICAN),
        ChildProfile("Alice", 12, ThemeType.FOX),
        ChildProfile("Bob", 60, ThemeType.ELEPHANT)
    )
}
