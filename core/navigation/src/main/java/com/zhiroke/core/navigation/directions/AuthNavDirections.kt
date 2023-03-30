package com.zhiroke.core.navigation.directions

import androidx.navigation.NamedNavArgument
import com.zhiroke.core.navigation.NavigationCommand

object AuthNavDirections {

    val auth = object : NavigationCommand {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "auth"
    }

}