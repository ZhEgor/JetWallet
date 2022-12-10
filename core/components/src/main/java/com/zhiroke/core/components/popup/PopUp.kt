package com.zhiroke.core.components.popup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.zhiroke.core.components.popup.components.Dash
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.utils.MaterialColor


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun PopUp(
    popUpState: PopUpState,
    onHide: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmStateChange = { newState ->
            if (newState == ModalBottomSheetValue.Hidden) {
                keyboardController?.hide()
                onHide.invoke()
            }
            true
        }
    )

    BackHandler(onBack = onHide)

    LaunchedEffect(key1 = popUpState) {
        if (popUpState.isShown) state.show() else if (state.isVisible) state.hide()
    }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(topStart = dp_16, topEnd = dp_16),
        sheetContent = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Dash()

                Box(modifier = Modifier.fillMaxSize(), content = content)
            }
        },
        sheetBackgroundColor = MaterialColor.background,
        content = {}
    )
}
