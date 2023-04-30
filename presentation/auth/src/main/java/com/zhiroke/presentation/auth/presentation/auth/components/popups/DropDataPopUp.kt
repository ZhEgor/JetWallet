package com.zhiroke.presentation.auth.presentation.auth.components.popups

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.zhiroke.core.components.popup.PopUp
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_64
import com.zhiroke.core.theme.utils.MaterialTypography
import com.zhiroke.presentation.auth.R
import com.zhiroke.presentation.auth.presentation.auth.components.popups.components.DropDataButton

@Composable
internal fun DropDataPopUp(popUpState: PopUpState, onHide: () -> Unit, onDropData: () -> Unit) {

    PopUp(popUpState = popUpState, heightFraction = 0.4f, onHide = onHide) {

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = dp_16),
            verticalArrangement = Arrangement.spacedBy(dp_16)
        ) {

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.dont_remember_pin),
                style = MaterialTypography.titleLarge
            )

            Text(
                text = stringResource(id = R.string.drop_data_warning),
                style = MaterialTypography.titleMedium
            )
        }

        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .padding(all = dp_16),
            horizontalArrangement = Arrangement.spacedBy(dp_16)
        ) {

            if (popUpState.isShown) {
                DropDataButton(
                    modifier = Modifier
                        .height(dp_64)
                        .weight(1f),
                    onClick = onDropData
                )
            }

            Button(
                modifier = Modifier
                    .height(dp_64)
                    .weight(1f),
                onClick = onHide
            ) {
                Text(
                    text = stringResource(id = R.string.button_drop_data_cancel),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}