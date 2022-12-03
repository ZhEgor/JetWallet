package com.zhiroke.core.components.popup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.core.components.popup.components.Dash
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.utils.MaterialColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopUp(
    popUpState: PopUpState,
    onHide: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {

    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmStateChange = { newState ->
            if (newState == ModalBottomSheetValue.Hidden) {
                onHide.invoke()
            }
            true
        }
    )

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
                    .fillMaxHeight(0.8f),
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
