package com.nanit.happybirthday.presentation.ipaddress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nanit.happybirthday.R
import com.nanit.happybirthday.presentation.common.UiEvent
import com.nanit.happybirthday.presentation.ipaddress.widget.IPFields
import com.nanit.happybirthday.presentation.ipaddress.widget.PortField

@Composable
fun IPAddressScreen(
    state: IPAddressUiState,
    onEvent: (UiEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var filledIPAddress by remember { mutableStateOf("") }
        var filledPort by remember { mutableIntStateOf(0) }

        val isConnectButtonEnabled = filledIPAddress.isNotBlank() && filledPort > 0

        Spacer(modifier = Modifier.height(48.dp))
        IPFields(
            label = stringResource(R.string.ip_address),
            onIPAddressFilled = { ipAddress -> filledIPAddress = ipAddress }
        )

        Spacer(modifier = Modifier.height(16.dp))
        PortField(
            label = stringResource(R.string.port),
            onPortFilled = { port -> filledPort = port }
        )

        Spacer(modifier = Modifier.height(32.dp))
        ErrorTextIfNeeded(state)
        Button(
            enabled = isConnectButtonEnabled && state != IPAddressUiState.Loading,
            onClick = { onEvent(IPAddressUiEvent.OnConnectionClick(filledIPAddress, filledPort)) }
        ) {
            Text(text = stringResource(state.getActionButtonText()))
        }
    }
}

@Composable
private fun ErrorTextIfNeeded(state: IPAddressUiState) {
    when (state) {
        IPAddressUiState.ErrorConnection -> {
            Text(text = stringResource(R.string.connection_error))
            Spacer(modifier = Modifier.height(8.dp))
        }

        IPAddressUiState.ErrorTheme -> {
            Text(text = stringResource(R.string.problem_with_getting_theme))
            Spacer(modifier = Modifier.height(8.dp))
        }

        else -> Unit
    }
}

private fun IPAddressUiState.getActionButtonText() =
    when (this) {
        IPAddressUiState.Empty, IPAddressUiState.ErrorConnection, IPAddressUiState.ErrorTheme -> {
            R.string.connect
        }

        IPAddressUiState.Loading -> {
            R.string.in_process
        }

        IPAddressUiState.Success -> {
            R.string.done
        }
    }