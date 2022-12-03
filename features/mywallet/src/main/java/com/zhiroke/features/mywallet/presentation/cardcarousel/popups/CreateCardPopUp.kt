package com.zhiroke.features.mywallet.presentation.cardcarousel.popups

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.components.buttons.ButtonText
import com.zhiroke.core.components.popup.PopUp
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.theme.demensions.dp_16


@Composable
internal fun CreateCardPopUp(popUpState: PopUpState, onHide: () -> Unit) {

    PopUp(popUpState = popUpState, onHide = onHide) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {


            ButtonText(modifier = Modifier.fillMaxWidth().padding(all = dp_16), text = "Create") {

            }
        }
    }
}
