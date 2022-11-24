package com.bove.martin.adoptapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.bove.martin.adoptapp.R

/**
 * Created by Martín Bove on 23/11/2022.
 * E-mail: mbove77@gmail.com
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComp(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Painter?,
    label: String,
    keyboardType: KeyboardType,
) {
    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(onClick = { }) {
                if (icon != null) {
                    Icon(icon, contentDescription = label)
                }
            }
        },
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassTextFieldComp(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Painter?,
    label: String,
) {
    var visibility by remember { mutableStateOf(false) }
    val iconVisibility = if (visibility) {
        painterResource(R.drawable.visibility_on)
    } else {
        painterResource(R.drawable.visibility_off)
    }

    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            IconButton(onClick = { }) {
                if (icon != null) {
                    Icon(icon, contentDescription = label)
                }
            }
        },
        trailingIcon = {
            IconButton(onClick = { visibility = !visibility }) {
                Icon(iconVisibility, contentDescription = "Pass icon")
            }
        },
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (visibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

