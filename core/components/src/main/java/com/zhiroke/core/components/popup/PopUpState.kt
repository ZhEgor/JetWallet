package com.zhiroke.core.components.popup

data class PopUpState(
    val isShown: Boolean
) {

    companion object {

        fun initialState() = PopUpState(isShown = false)
    }
}
