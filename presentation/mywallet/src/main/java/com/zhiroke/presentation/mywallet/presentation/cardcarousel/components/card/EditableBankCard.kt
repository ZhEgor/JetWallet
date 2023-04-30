package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.zhiroke.core.theme.demensions.dp_24
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.domain.models.BankCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.back.ImmutableBackSideCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.front.ImmutableFrontSideCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper


@Composable
internal fun EditableBankCard(bankCard: BankCard, isFrontSide: Boolean, onPhotoUriChange: (Uri?) -> Unit) {

    val context = LocalContext.current
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia() ,
        onResult = { uri ->
            uri?.let {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION 
                context.contentResolver.takePersistableUriPermission(it, flag)
            }
            onPhotoUriChange.invoke(uri)
        }
    )

    RotatingCardWrapper(initialIsFrontSide = isFrontSide) { _isFrontSide ->

        if (_isFrontSide) {
            ImmutableFrontSideCard(bankCard = bankCard) {

                if (bankCard.skinUri == null) {
                    PickSkinButton {
                        singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                } else {
                    DeleteSkinButton {
                        onPhotoUriChange.invoke(null)
                    }
                }
            }
        } else {
            ImmutableBackSideCard(bankCard = bankCard)
        }
    }
}

@Composable
private fun PickSkinButton(modifier: Modifier = Modifier, onClick: () -> Unit) {

    IconButton(modifier = modifier, onClick = onClick) {

        Icon(
            modifier = Modifier.size(dp_24),
            imageVector = Icons.Rounded.AddPhotoAlternate,
            contentDescription = null,
            tint = MaterialColor.primary
        )
    }
}

@Composable
private fun DeleteSkinButton(modifier: Modifier = Modifier, onClick: () -> Unit) {

    IconButton(modifier = modifier, onClick = onClick) {

        Icon(
            modifier = Modifier.size(dp_24),
            imageVector = Icons.Rounded.Close,
            contentDescription = null,
            tint = MaterialColor.primary
        )
    }
}