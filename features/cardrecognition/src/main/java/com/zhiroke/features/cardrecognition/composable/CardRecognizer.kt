package com.zhiroke.features.cardrecognition.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.common.permissions.RequestPermissionHelper

@Composable
fun CardRecognizer(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onFrontSideCardRecognized: (cardNumber: String, expirationDate: String) -> Unit,
    onBackSideCardRecognized: (verificationNumber: String) -> Unit
) {

    RequestPermissionHelper(
        permission = android.Manifest.permission.CAMERA,
        permissionNotGrantedContent = { onRequestPermission ->
            Rationale(onDismissRequest = onDismissRequest, onRequestPermission = onRequestPermission)
        },
        permissionGrantedContent = {
            UnsafeCardRecognizer(
                modifier = modifier,
                onFrontSideCardRecognized = onFrontSideCardRecognized,
                onBackSideCardRecognized = onBackSideCardRecognized
            )
        }
    )
}