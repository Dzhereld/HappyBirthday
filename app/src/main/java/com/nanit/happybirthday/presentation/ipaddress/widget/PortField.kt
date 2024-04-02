package com.nanit.happybirthday.presentation.ipaddress.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

@Composable
fun PortField(label: String, onPortFilled: (Int) -> Unit) {
    var port by remember { mutableStateOf("") }
    Text(text = "$label:")
    Spacer(modifier = Modifier.height(8.dp))
    Row {
        TextField(
            shape = RoundedCornerShape(8.dp),
            value = port,
            onValueChange = {
                if (it.length <= 4) {
                    port = it
                    if (port.isPortFormat())
                        onPortFilled(port.toInt())
                    else
                        onPortFilled(0)
                }
            },
            modifier = Modifier.width(80.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

private fun String.isPortFormat() = isNotEmpty() && isDigitsOnly()