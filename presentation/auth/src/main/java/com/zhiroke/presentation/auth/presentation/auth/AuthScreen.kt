package com.zhiroke.presentation.auth.presentation.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhiroke.core.components.toasts.ErrorToast
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_32
import com.zhiroke.core.theme.demensions.dp_64
import com.zhiroke.core.theme.utils.MaterialTypography
import com.zhiroke.presentation.auth.R
import com.zhiroke.presentation.auth.presentation.auth.components.buttons.TertiaryTextButton
import com.zhiroke.presentation.auth.presentation.auth.components.dialkeyboard.DialKeyboardBlock
import com.zhiroke.presentation.auth.presentation.auth.components.overlays.LockedScreenOverlay
import com.zhiroke.presentation.auth.presentation.auth.components.passwordfield.PINField
import com.zhiroke.presentation.auth.presentation.auth.components.popups.DropDataPopUp
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthRoute() {

    AuthScreen()
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun AuthScreen(viewModel: AuthViewModel = getViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        when (state.screenStatus) {
            AuthState.AuthScreenStatus.LOG_IN -> LoginBlock(viewModel = viewModel, state = state.loginState)
            AuthState.AuthScreenStatus.SET_PIN -> SetPINBlock(viewModel = viewModel, state = state.setPINState)
            AuthState.AuthScreenStatus.CONFIRM_PIN -> ConfirmPINBlock(viewModel = viewModel, state = state.confirmPINState)
        }
    }

    ErrorToast(text = state.errorMessage)
}

@Composable
private fun BoxScope.LoginBlock(viewModel: AuthViewModel, state: AuthState.LoginState) {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = dp_32)
            .align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(space = dp_32, alignment = Alignment.Bottom)
    ) {

        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = stringResource(R.string.enter_pin_code)
        )

        PINField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pin = state.pin,
            visible = false
        )

        DialKeyboardBlock(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            biometricAuthSupported = state.isBioSupported,
            onCommand = viewModel::executeCommand,
            onBioClick = viewModel::authWithBio,
        )

        TextButton(onClick = viewModel::showDropDataPopUp) {

            Text(text = stringResource(R.string.dont_remember_pin))
        }
    }

    DropDataPopUp(popUpState = state.dropDataPopUpState, onHide = viewModel::hideDropDataPopUp, onDropData = viewModel::dropAllData)

    if (state.secondsLeftToBeUnlocked != null) {
        LockedScreenOverlay(text = stringResource(id = R.string.placeholder_try_again, state.secondsLeftToBeUnlocked))
    }
}

@Composable
private fun BoxScope.SetPINBlock(viewModel: AuthViewModel, state: AuthState.SetPINState) {

    TertiaryTextButton(
        modifier = Modifier
            .height(height = dp_64)
            .align(Alignment.TopEnd)
            .padding(top = dp_16, end = dp_16),
        resId = R.string.btn_next,
        fontSize = MaterialTypography.labelLarge.fontSize,
        enabled = state.pin.length == 4,
        onClick = viewModel::changeScreenStatusToConfirmPIN
    )

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = dp_32)
            .align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(space = dp_32, alignment = Alignment.Bottom)
    ) {

        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = stringResource(R.string.set_pin_code)
        )

        PINField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pin = state.pin,
            visible = true
        )

        DialKeyboardBlock(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            biometricAuthSupported = false,
            onCommand = viewModel::executeCommand,
            onBioClick = viewModel::authWithBio,
        )
    }
}

@Composable
private fun BoxScope.ConfirmPINBlock(viewModel: AuthViewModel, state: AuthState.ConfirmPINState) {

    BackHandler(onBack = viewModel::changeScreenStatusToSetPIN)

    TertiaryTextButton(
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(top = dp_16, start = dp_16),
        resId = R.string.btn_back,
        fontSize = MaterialTypography.labelLarge.fontSize,
        enabled = true,
        onClick = viewModel::changeScreenStatusToSetPIN
    )

    TertiaryTextButton(
        modifier = Modifier
            .height(height = dp_64)
            .align(Alignment.TopEnd)
            .padding(top = dp_16, end = dp_16),
        resId = R.string.btn_confirm,
        fontSize = MaterialTypography.labelLarge.fontSize,
        enabled = state.pin.length == 4,
        onClick = viewModel::confirmPIN
    )

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = dp_32)
            .align(Alignment.BottomCenter),
        verticalArrangement = Arrangement.spacedBy(space = dp_32, alignment = Alignment.Bottom)
    ) {

        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = stringResource(R.string.confirm_pin_code)
        )

        PINField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pin = state.pin,
            visible = true
        )

        DialKeyboardBlock(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            biometricAuthSupported = false,
            onCommand = viewModel::executeCommand,
            onBioClick = viewModel::authWithBio,
        )
    }
}