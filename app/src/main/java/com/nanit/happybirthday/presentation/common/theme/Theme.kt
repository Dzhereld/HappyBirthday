package com.nanit.happybirthday.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.nanit.happybirthday.R

private val LightElephantYellowColorScheme = lightColorScheme(
    background = Light_Yellow
)

private val LightFoxGreenColorScheme = lightColorScheme(
    background = Light_Green
)

private val LightPelicanBlueColorScheme = lightColorScheme(
    background = Light_Blue
)

private val NeutralColorScheme = lightColorScheme(
    primary = Light_Neutral_Primary,
    secondary = Light_Neutral_Secondary,
    tertiary = Light_Neutral_Tertiary,
    background = Light_Neutral
)

@Composable
fun NeutralTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = NeutralColorScheme
    HappyBirthdayTheme(darkTheme, colors, content)
}

@Composable
fun ElephantYellowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = LightElephantYellowColorScheme
    CompositionLocalProvider(
        LocalImages provides LightElephantImages
    ) {
        HappyBirthdayTheme(darkTheme, colors, content)
    }
}

@Composable
fun FoxGreenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = LightFoxGreenColorScheme
    CompositionLocalProvider(
        LocalImages provides LightFoxImages
    ) {
        HappyBirthdayTheme(darkTheme, colors, content)
    }
}

@Composable
fun PelicanBlueTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = LightPelicanBlueColorScheme
    CompositionLocalProvider(
        LocalImages provides LightPelicanImages
    ) {
        HappyBirthdayTheme(darkTheme, colors, content)
    }
}

private val LightElephantImages = Images(
    backgroundImage = R.drawable.bg_elephant_yellow,
    addPhotoIcon = R.drawable.ic_add_photo_yellow,
    faceHolder = R.drawable.ic_face_yellow,
)

private val LightFoxImages = Images(
    backgroundImage = R.drawable.bg_fox_green,
    addPhotoIcon = R.drawable.ic_add_photo_green,
    faceHolder = R.drawable.ic_face_green,
)

private val LightPelicanImages = Images(
    backgroundImage = R.drawable.bg_pelican_blue,
    addPhotoIcon = R.drawable.ic_add_photo_blue,
    faceHolder = R.drawable.ic_face_blue,
)

@Composable
fun HappyBirthdayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: ColorScheme,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

object ChildProfileTheme {
    val images: Images
        @Composable
        get() = LocalImages.current
}