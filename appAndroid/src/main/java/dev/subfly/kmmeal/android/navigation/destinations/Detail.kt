package dev.subfly.kmmeal.android.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dev.subfly.kmmeal.android.common.utils.constants.NavConstants
import dev.subfly.kmmeal.android.common.utils.enums.NavParams
import dev.subfly.kmmeal.android.navigation.destinations.base.BaseDestination
import dev.subfly.kmmeal.android.navigation.destinations.base.ScreenRouted

sealed class Detail: ScreenRouted {

    override val screenRoute: String
        get() = "detail_route"

    object Meal: BaseDestination() {
        override val route: String
            get() = createRoute(
                route = "meal_detail",
                parameters = listOf(NavParams.ID)
            )
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(
                    name = NavParams.ID.name
                ) {
                    type = NavType.StringType
                    defaultValue = NavConstants.DEFAULT_STRING_ARG_VALUE
                }
            )

        fun passArguments(mealId: String) = createRouteWithParams(
            route = "meal_detail",
            arguments = listOf(NavParams.ID),
            params = listOf(mealId)
        )
    }

    object Category: BaseDestination() {
        override val route: String
            get() = createRoute(
                route = "category_detail",
                parameters = listOf(NavParams.CATEGORY_NAME)
            )
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(
                    name = NavParams.CATEGORY_NAME.name
                ) {
                    type = NavType.StringType
                    defaultValue = NavConstants.DEFAULT_STRING_ARG_VALUE
                }
            )
        fun passArguments(categoryName: String) = createRouteWithParams(
            route = "category_detail",
            arguments = listOf(NavParams.CATEGORY_NAME),
            params = listOf(categoryName)
        )
    }

}