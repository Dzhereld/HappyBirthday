package com.nanit.happybirthday.presentation.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nanit.happybirthday.presentation.ipaddress.IPAddressScreen
import com.nanit.happybirthday.presentation.ipaddress.IPAddressUiState
import com.nanit.happybirthday.presentation.common.theme.HappyBirthdayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChildProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ChildProfileViewModel = viewModel()

            val uiState = viewModel.uiState.value
            if (uiState as? ChildProfileUiState == null) {
                return@setContent
            }

            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChildProfileScreen(
                        state = uiState,
                        onEvent = { uiEvent -> viewModel.handleUiEvent(uiEvent) }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "original image", device = "spec:shape=Normal,width=361,height=592,unit=dp,dpi=480")
fun GreetingPreview() {
    HappyBirthdayTheme {
        IPAddressScreen(IPAddressUiState.Empty)
    }
}