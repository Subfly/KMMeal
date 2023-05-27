package dev.subfly.kmmeal.android.common.utils.extensions

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import dev.subfly.kmmeal.android.navigation.destinations.base.BaseDestination

fun NavHostController.navigate(to: BaseDestination) {
    this.navigate(to.route)
}

fun NavHostController.navigate(to: BaseDestination, withOptions: NavOptionsBuilder.() -> Unit) {
    this.navigate(to.route, withOptions)
}