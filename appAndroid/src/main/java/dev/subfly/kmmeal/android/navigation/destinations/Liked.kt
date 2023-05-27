package dev.subfly.kmmeal.android.navigation.destinations

import androidx.navigation.NamedNavArgument
import dev.subfly.kmmeal.android.navigation.destinations.base.BaseDestination
import dev.subfly.kmmeal.android.navigation.destinations.base.ScreenRouted

object Liked: BaseDestination(), ScreenRouted {
    override val route: String
        get() = "liked_meals"
    override val arguments: List<NamedNavArgument>
        get() = emptyList()
    override val screenRoute: String
        get() = "liked_route"
}