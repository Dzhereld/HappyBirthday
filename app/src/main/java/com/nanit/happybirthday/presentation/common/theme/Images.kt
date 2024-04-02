package com.nanit.happybirthday.presentation.common.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import com.nanit.happybirthday.R

/**
 * Images that can vary by theme.
 */
@Immutable
data class Images(
    @DrawableRes val backgroundImage: Int,
    @DrawableRes val addPhotoIcon: Int,
    @DrawableRes val faceHolder: Int,
    @DrawableRes val logo: Int = R.drawable.nanit_title,
    @DrawableRes val leftSwirls: Int = R.drawable.left_swirls,
    @DrawableRes val rightSwirls: Int = R.drawable.right_swirls,

    val elevenNumberImageMap: Map<Int, Int> = mapOf(
        1 to R.drawable.n_1,
        2 to R.drawable.n_2,
        3 to R.drawable.n_3,
        4 to R.drawable.n_4,
        5 to R.drawable.n_5,
        6 to R.drawable.n_6,
        7 to R.drawable.n_7,
        8 to R.drawable.n_8,
        9 to R.drawable.n_9,
        10 to R.drawable.n_10,
        11 to R.drawable.n_11,
    )
)

internal val LocalImages = staticCompositionLocalOf<Images> {
    error("No LocalImages specified")
}