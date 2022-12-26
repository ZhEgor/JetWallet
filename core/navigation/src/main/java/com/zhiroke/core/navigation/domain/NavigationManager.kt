package com.zhiroke.core.navigation.domain

import androidx.navigation.NavHostController
import com.zhiroke.core.navigation.NavigationCommand

class NavigationManager {

    private var navController: NavHostController? = null

    fun navigateTo(direction: NavigationCommand) {
        navController?.navigate(route = direction.destination)
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