package com.nanit.happybirthday.presentation.profile.util

import androidx.compose.ui.tooling.preview.Preview


@Preview(name = "original image", device = "spec:shape=Normal,width=361,height=592,unit=dp,dpi=480")
// @Preview(name = "PIXEL_4_XL", device = Devices.PIXEL_4_XL)
// @Preview(name = "NEXUS_7", device = Devices.NEXUS_7)
// @Preview(name = "NEXUS_7_2013", device = Devices.NEXUS_7_2013)
// @Preview(name = "NEXUS_5", device = Devices.NEXUS_5)
// @Preview(name = "NEXUS_6", device = Devices.NEXUS_6)
// @Preview(name = "NEXUS_5X", device = Devices.NEXUS_5X)
// @Preview(name = "NEXUS_6P", device = Devices.NEXUS_6P)
// @Preview(name = "PIXEL", device = Devices.PIXEL)
// @Preview(name = "PIXEL_XL", device = Devices.PIXEL_XL)
// @Preview(name = "PIXEL_2", device = Devices.PIXEL_2)
// @Preview(name = "PIXEL_2_XL", device = Devices.PIXEL_2_XL)
// @Preview(name = "PIXEL_3", device = Devices.PIXEL_3)
// @Preview(name = "PIXEL_3_XL", device = Devices.PIXEL_3_XL)
// @Preview(name = "PIXEL_3A", device = Devices.PIXEL_3A)
// @Preview(name = "PIXEL_3A_XL", device = Devices.PIXEL_3A_XL)
// @Preview(name = "PIXEL_4", device = Devices.PIXEL_4)
annotation class DevicesPreview


object Devices {
    const val DEFAULT = ""

    const val NEXUS_7 = "id:Nexus 7"
    const val NEXUS_7_2013 = "id:Nexus 7 2013"
    const val NEXUS_5 = "id:Nexus 5"
    const val NEXUS_6 = "id:Nexus 6"
    const val NEXUS_9 = "id:Nexus 9"
    const val NEXUS_10 = "name:Nexus 10"
    const val NEXUS_5X = "id:Nexus 5X"
    const val NEXUS_6P = "id:Nexus 6P"
    const val PIXEL_C = "id:pixel_c"
    const val PIXEL = "id:pixel"
    const val PIXEL_XL = "id:pixel_xl"
    const val PIXEL_2 = "id:pixel_2"
    const val PIXEL_2_XL = "id:pixel_2_xl"
    const val PIXEL_3 = "id:pixel_3"
    const val PIXEL_3_XL = "id:pixel_3_xl"
    const val PIXEL_3A = "id:pixel_3a"
    const val PIXEL_3A_XL = "id:pixel_3a_xl"
    const val PIXEL_4 = "id:pixel_4"
    const val PIXEL_4_XL = "id:pixel_4_xl"
    const val AUTOMOTIVE_1024p = "id:automotive_1024p_landscape"
}