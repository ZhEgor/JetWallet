package com.zhiroke.core.cardrecognition.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zhiroke.core.cardrecognition.R


@Composable
internal fun Rationale(
    onDismissRequest: () -> Unit,
    onRequestPermission: () -> Unit,
) {

    AlertDialog(
        title = {
            Text(text = stringResource(R.string.rational_permission_request_title))
        },
        text = {
            Text(text = stringResource(R.string.rational_permission_request_rational_text))
        },
        confirmButton = {
            Button(onClick = onRequestPermission) {

                Text(stringResource(R.string.rational_permission_request_confirm_button))
            }
        },
        onDismissRequest = onDismissRequest
    )
}
