package com.zhiroke.core.navigation.utils

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.koin.core.context.GlobalContext
import org.koin.dsl.binds
import org.koin.dsl.module

fun setNavControllerIntoDi(navController: NavHostController) {
    GlobalContext.get().loadModules(modules = listOf(
        module {
            single { navController } binds arrayOf(NavController::class, NavHostController::class)
        }
    ), allowOverride = true)
}

fun getNavController(): NavHostController {
    return GlobalContext.get().get<NavHostController>()
}