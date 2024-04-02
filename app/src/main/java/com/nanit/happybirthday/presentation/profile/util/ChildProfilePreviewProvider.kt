package com.nanit.happybirthday.presentation.profile.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ThemeType
import java.util.Date

class ChildProfilePreviewProvider : PreviewParameterProvider<ChildProfile> {
    override val values: Sequence<ChildProfile> = sequenceOf(
        ChildProfile("John", Date(1706877222), ThemeType.PELICAN),
        ChildProfile("Alice", Date(1675341222), ThemeType.FOX),
        ChildProfile("Bob", Date(1549110822), ThemeType.ELEPHANT)
    )
}
