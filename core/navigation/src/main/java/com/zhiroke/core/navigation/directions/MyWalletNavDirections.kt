package com.zhiroke.core.navigation.directions

import androidx.navigation.NamedNavArgument
import com.zhiroke.core.navigation.NavigationCommand

object MyWalletNavDirections {

    val cardCarousel = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "cardCarousel"
    }

    val splash = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "splash"
    }

}