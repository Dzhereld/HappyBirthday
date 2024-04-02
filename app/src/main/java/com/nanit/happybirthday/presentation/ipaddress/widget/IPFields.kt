package com.nanit.happybirthday.presentation.ipaddress.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@Composable
fun IPFields(label: String, onIPAddressFilled: (String) -> Unit) {
    val ipFields = remember { mutableStateListOf("", "", "", "") }
    Text(text = "$label:")
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in ipFields.indices) {
            TextField(
                value = ipFields[i],
                shape = RoundedCornerShape(8.dp),
                onValueChange = { text ->
                    if (text.length <= 3) {
                        ipFields[i] = text
                        if (ipFields.isIPAddressFormat())
                            onIPAddressFilled(ipFields.joinToString("."))
                        else
                            onIPAddressFilled("")
                    }
                },
                modifier = Modifier.width(65.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            if (i < ipFields.size - 1) {
                Text(text = ".", fontSize = 30.sp)
            }
        }
    }
}

private fun List<String>.isIPAddressFormat() = filter { it.isNotEmpty() && it.isDigitsOnly() }.size == 4