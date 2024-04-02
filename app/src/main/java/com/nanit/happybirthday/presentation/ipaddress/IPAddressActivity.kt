package com.nanit.happybirthday.presentation.ipaddress

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nanit.happybirthday.presentation.common.theme.NeutralTheme
import com.nanit.happybirthday.presentation.birthday.BirthdayActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IPAddressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: IPAddressViewModel = viewModel()

            val uiState = viewModel.uiState.value
            if (uiState == IPAddressUiState.OpenBirthdayScreen) {
                with(LocalContext.current) {
                    startActivity(Intent(this, BirthdayActivity::class.java)
                        .apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                }
            }

            NeutralTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IPAddressScreen(
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
    NeutralTheme {
        IPAddressScreen(IPAddressUiState.Empty)
    }
}