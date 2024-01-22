package com.example.shiftlab_task.presentation.screen.registration.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shiftlab_task.presentation.ui.theme.ErrorAccentColor
import com.example.shiftlab_task.presentation.ui.theme.RedColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordCustomTextField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    transformationState: Boolean,
    onButtonClick: () -> Unit,
    errorText: String? = null,
    modifier: Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    onValueChange(it)
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if (transformationState)
                    VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onButtonClick()
                        }
                    ) {
                        Icon(
                            imageVector = if (transformationState) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                label =  { Text(text = labelText) },
                isError = errorText != null,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    errorBorderColor = RedColor,
                    errorContainerColor = RedColor.copy(alpha = 0.1f)
                ),
                textStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W400)
            )
            errorText?.let {
                Text (
                    text = it,
                    modifier = Modifier
                        .padding(top = 8.dp),
                    color = RedColor,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400)
                )
            }
        }
    }
}