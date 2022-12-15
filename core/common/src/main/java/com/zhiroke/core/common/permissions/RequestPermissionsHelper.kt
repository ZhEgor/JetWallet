package com.zhiroke.core.common.permissions

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissionHelper(
    permission: String,
    permissionNotGrantedContent: @Composable (requestPermission: () -> Unit) -> Unit = { },
    permissionGrantedContent: @Composable () -> Unit = { }
) {
    val permissionState = rememberPermissionState(permission)

    if (permissionState.status.isGranted) {
        permissionGrantedContent.invoke()
    } else {
        permissionNotGrantedContent.invoke {
            permissionState.launchPermissionRequest()
        }
    }
}