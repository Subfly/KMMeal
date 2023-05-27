package dev.subfly.kmmeal.android.navigation.destinations

import androidx.navigation.NamedNavArgument
import dev.subfly.kmmeal.android.navigation.destinations.base.BaseDestination
import dev.subfly.kmmeal.android.navigation.destinations.base.ScreenRouted

object RandomMeal: BaseDestination(), ScreenRouted {
    override val route: String
        get() = "random_meal"
    override val arguments: List<NamedNavArgument>
        get() = emptyList()
    override val screenRoute: String
        get() = "random_meal_route"
}