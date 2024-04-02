package com.nanit.happybirthday.presentation.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.nanit.happybirthday.R
import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ThemeType
import com.nanit.happybirthday.presentation.common.UiEvent
import com.nanit.happybirthday.presentation.common.theme.ChildProfileTheme
import com.nanit.happybirthday.presentation.common.theme.ElephantYellowTheme
import com.nanit.happybirthday.presentation.common.theme.FoxGreenTheme
import com.nanit.happybirthday.presentation.common.theme.PelicanBlueTheme
import com.nanit.happybirthday.presentation.profile.util.ChildProfilePreviewProvider
import com.nanit.happybirthday.presentation.profile.util.DevicesPreview

@Composable
fun ChildProfileScreen(
    childProfile: ChildProfile,
    onEvent: (UiEvent) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val localDensity = LocalDensity.current

            var childPhotoSizeDp by remember {
                mutableStateOf(0.dp)
            }

            var childPhotoBottomSpaceHeightSizeDp by remember {
                mutableStateOf(0.dp)
            }

            var logoWidthSizeDp by remember {
                mutableStateOf(0.dp)
            }
            var numberImageHeightSizeDp by remember {
                mutableStateOf(0.dp)
            }

            var swirelsWidthSizeDp by remember {
                mutableStateOf(0.dp)
            }

            val backgroundRef = createRef()
            val ageTextSectionRef = createRef()
            val childPhotoRef = createRef()
            val logoRef = createRef()

            ChildPhoto(
                childPhotoRef= childPhotoRef,
                backgroundImageRef= backgroundRef,
                childPhotoSizeDp= childPhotoSizeDp,
                childPhotoBottomSpaceHeightSizeDp = childPhotoBottomSpaceHeightSizeDp
            )

            Background(
                backgroundRef = backgroundRef,
                onGloballyPositioned = { coordinates ->
                    with(localDensity) {
                        childPhotoBottomSpaceHeightSizeDp = (coordinates.size.height * 0.22f).toDp()
                        childPhotoSizeDp = (coordinates.size.width * 0.5678f).toDp()
                        logoWidthSizeDp = (coordinates.size.width * 0.1625f).toDp()
                        numberImageHeightSizeDp = (coordinates.size.height * 0.1485f).toDp()
                        swirelsWidthSizeDp = (coordinates.size.width * 0.1390f).toDp()
                    }
                })

            Logo(
                logoRef = logoRef,
                childPhotoRef = childPhotoRef,
                logoWidthSizeDp = logoWidthSizeDp,
                childPhotoSizeDp = childPhotoSizeDp
            )

            AgeTextSection(
                childProfile = childProfile,
                nameDateBirthColumnRef = ageTextSectionRef,
                childPhotoRef = childPhotoRef,
                numberImageHeightSizeDp = numberImageHeightSizeDp,
                swirelsWidthSizeDp = swirelsWidthSizeDp
            )
        }
    }
}

@Composable
fun ConstraintLayoutScope.ChildPhoto(
    childPhotoRef: ConstrainedLayoutReference,
    backgroundImageRef: ConstrainedLayoutReference,
    childPhotoSizeDp: Dp,
    childPhotoBottomSpaceHeightSizeDp: Dp,
) {
    Column(
        modifier = Modifier
            .constrainAs(childPhotoRef) {
                bottom.linkTo(backgroundImageRef.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = ChildProfileTheme.images.faceHolder),
            contentDescription = "Child Photo Image",
            modifier = Modifier
                .width(childPhotoSizeDp)
                .height(childPhotoSizeDp)
        )
        Spacer(
            modifier = Modifier
                .height(childPhotoBottomSpaceHeightSizeDp)
        )
    }
}

@Composable
private fun ConstraintLayoutScope.Background(
    backgroundRef: ConstrainedLayoutReference,
    onGloballyPositioned: (LayoutCoordinates) -> Unit
) {
    Image(
        painter = painterResource(id = ChildProfileTheme.images.backgroundImage),
        contentDescription = "Background Image",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
            .constrainAs(backgroundRef) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            .onGloballyPositioned(onGloballyPositioned)
    )
}

@Composable
private fun ConstraintLayoutScope.Logo(
    logoRef: ConstrainedLayoutReference,
    childPhotoRef: ConstrainedLayoutReference,
    logoWidthSizeDp: Dp,
    childPhotoSizeDp: Dp,
){
    Image(
        painter = painterResource(id = ChildProfileTheme.images.logo),
        contentDescription = "Logo Image",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .constrainAs(logoRef) {
                top.linkTo(childPhotoRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .width(logoWidthSizeDp)
            .padding(top = childPhotoSizeDp + 15.dp)
    )
}

@Composable
private fun ConstraintLayoutScope.AgeTextSection(
    childProfile: ChildProfile,
    nameDateBirthColumnRef: ConstrainedLayoutReference,
    childPhotoRef: ConstrainedLayoutReference,
    numberImageHeightSizeDp: Dp,
    swirelsWidthSizeDp: Dp,
){
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(
                top = 20.dp,
                bottom = 15.dp,
                start = 50.dp,
                end = 50.dp
            )
            .constrainAs(nameDateBirthColumnRef) {
                bottom.linkTo(childPhotoRef.top)
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val ageInYears = childProfile.ageInMonths / 12
        val ageImage = if (ageInYears > 0) {
            ChildProfileTheme.images.elevenNumberImageMap[ageInYears]
        } else {
            ChildProfileTheme.images.elevenNumberImageMap[childProfile.ageInMonths]
        }

        TodayIsTitle(childProfile.name)

        AgeImage(
            swirelsWidthSizeDp = swirelsWidthSizeDp,
            ageImage = ageImage,
            numberImageHeightSizeDp = numberImageHeightSizeDp)

        AgeOldTitle(ageInYears, childProfile.ageInMonths)
    }

}

@Composable
private fun AgeOldTitle(
    ageInYears: Int,
    ageInMonths: Int,
) {
    Text(
        if (ageInYears > 0) {
            pluralStringResource(
                id = R.plurals.years_old,
                count = ageInYears
            ).uppercase()
        } else {
            pluralStringResource(
                id = R.plurals.months_old,
                count = ageInMonths
            ).uppercase()
        },
        fontSize = 28.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun AgeImage(
    swirelsWidthSizeDp: Dp,
    ageImage: Int?,
    numberImageHeightSizeDp: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 13.dp,
                bottom = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Swirels(
            imageId = ChildProfileTheme.images.leftSwirls,
            widthSizeDp = swirelsWidthSizeDp,
            contentDescription = "Left Swirels image",
        )
        ageImage?.let {
            Image(
                painter = painterResource(id = ageImage),
                contentDescription = "Age image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .height(numberImageHeightSizeDp)
            )
        }
        Swirels(
            imageId = ChildProfileTheme.images.rightSwirls,
            widthSizeDp = swirelsWidthSizeDp,
            contentDescription = "Right Swirels image",
        )
    }
}

@Composable
private fun Swirels(
    @DrawableRes imageId: Int,
    widthSizeDp: Dp,
    contentDescription: String? = null
) {
    Image(
        painter = painterResource(imageId),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.width(widthSizeDp),
        contentDescription = contentDescription
    )
}

@Composable
private fun TodayIsTitle(name: String) {
    Text(
        stringResource(
            R.string.today_is,
            name.uppercase()
        ),
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
@DevicesPreview
fun ChildProfileScreenPreview(
    @PreviewParameter(
        ChildProfilePreviewProvider::class,
        1
    ) childProfile: ChildProfile
) {
    when (childProfile.theme) {
        ThemeType.PELICAN -> PelicanBlueTheme { ChildProfileScreen(childProfile) }
        ThemeType.FOX -> FoxGreenTheme { ChildProfileScreen(childProfile) }
        ThemeType.ELEPHANT -> ElephantYellowTheme { ChildProfileScreen(childProfile) }
        ThemeType.UNDEFINED -> Unit
    }
}