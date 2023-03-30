package com.zhiroke.core.navigation.domain

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.zhiroke.core.navigation.NavigationCommand

class NavigationManager {

    private var navController: NavHostController? = null

    fun navigateTo(direction: NavigationCommand, builder: NavOptionsBuilder.() -> Unit = {}) {
        navController?.navigate(route = direction.destination, builder = builder)
    }

    fun popBackStack() {
        navController?.popBackStack()
    }

    fun attachNavController(navController: NavHostController) {
        this.navController = navController
    }

    fun detachNavController() {
        navController = null
    }
}