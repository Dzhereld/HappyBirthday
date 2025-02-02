package com.nanit.happybirthday.presentation.birthday

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nanit.happybirthday.R
import com.nanit.happybirthday.domain.entity.BirthdayEvent
import com.nanit.happybirthday.domain.entity.ThemeType.ELEPHANT
import com.nanit.happybirthday.domain.entity.ThemeType.FOX
import com.nanit.happybirthday.domain.entity.ThemeType.PELICAN
import com.nanit.happybirthday.domain.entity.ThemeType.UNDEFINED
import com.nanit.happybirthday.presentation.common.UiEvent
import com.nanit.happybirthday.presentation.common.theme.ElephantYellowTheme
import com.nanit.happybirthday.presentation.common.theme.FoxGreenTheme
import com.nanit.happybirthday.presentation.common.theme.NeutralTheme
import com.nanit.happybirthday.presentation.common.theme.PelicanBlueTheme
import com.nanit.happybirthday.presentation.birthday.util.BirthdayPreviewProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BirthdayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                getColor(R.color.status_bar)
            )
        )
        setContent {
            val viewModel: BirthdayViewModel = viewModel()

            val uiState = viewModel.uiState
                .collectAsState(initial = BirthdayUiState.Empty)
                .value

            if (uiState as? BirthdayUiState == null) {
                return@setContent
            }

            val data = when (uiState) {
                BirthdayUiState.Empty -> return@setContent
                is BirthdayUiState.Error -> {
                    val errorMessage = "An error occurred while processing the received message."
                    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_LONG).show()
                    uiState.oldData
                }

                is BirthdayUiState.Filled -> uiState.data
            }

            val onEvent: (UiEvent) -> Unit = { uiEvent -> viewModel.handleUiEvent(uiEvent) }
            when(data.theme) {
                PELICAN -> PelicanBlueTheme { BirthdayScreen(data, onEvent) }
                FOX -> FoxGreenTheme { BirthdayScreen(data, onEvent) }
                ELEPHANT -> ElephantYellowTheme { BirthdayScreen(data, onEvent) }
                UNDEFINED -> NeutralTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Text(text = stringResource(R.string.please_update_the_app))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "original image", device = "spec:shape=Normal,width=361,height=592,unit=dp,dpi=480")
fun BirthdayScreenPreview(@PreviewParameter(BirthdayPreviewProvider::class) birthdayEvent: BirthdayEvent) {
    when(birthdayEvent.theme) {
        PELICAN -> PelicanBlueTheme { BirthdayScreen(birthdayEvent) }
        FOX -> FoxGreenTheme { BirthdayScreen(birthdayEvent) }
        ELEPHANT -> ElephantYellowTheme { BirthdayScreen(birthdayEvent) }
        UNDEFINED -> Unit
    }
}