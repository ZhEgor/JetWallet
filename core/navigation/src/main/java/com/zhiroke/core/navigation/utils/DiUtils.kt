package com.zhiroke.core.navigation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.zhiroke.core.navigation.domain.NavigationManager
import org.koin.core.context.GlobalContext

fun attachNavController(navController: NavHostController) {
    getNavigationManager().attachNavController(navController = navController)
}

fun detachNavController() {
    getNavigationManager().detachNavController()
}

@Composable
fun rememberNavigationManager(): NavigationManager {
    return remember(calculation = ::getNavigationManager)
}

private fun getNavigationManager(): NavigationManager {
    return GlobalContext.get().get<NavigationManager>()
}
