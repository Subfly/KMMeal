package dev.subfly.kmmeal.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.subfly.kmmeal.android.app.LocalGlobalNavigator
import dev.subfly.kmmeal.android.common.utils.enums.NavParams
import dev.subfly.kmmeal.android.navigation.destinations.Detail
import dev.subfly.kmmeal.android.navigation.destinations.Home
import dev.subfly.kmmeal.android.navigation.destinations.Liked
import dev.subfly.kmmeal.android.navigation.destinations.RandomMeal
import dev.subfly.kmmeal.android.view.categoryDetail.CategoryDetailScreen
import dev.subfly.kmmeal.android.view.home.HomeScreen
import dev.subfly.kmmeal.android.view.liked.LikedMealScreen
import dev.subfly.kmmeal.android.view.mealDetail.MealDetailScreen
import dev.subfly.kmmeal.android.view.randomMeal.RandomMealScreen

@Composable
fun KMMNavHost() {
    val controller = LocalGlobalNavigator.current

    NavHost(
        navController = controller,
        route = Home.screenRoute,
        startDestination = Home.route
    ) {
        composable(
            route = Home.route,
            arguments = Home.arguments
        ) {
            HomeScreen()
        }
        composable(
            route = Liked.route,
            arguments = Liked.arguments
        ) {
            LikedMealScreen()
        }
        composable(
            route = RandomMeal.route,
            arguments = RandomMeal.arguments
        ) {
            RandomMealScreen()
        }
        composable(
            route = Detail.Category.route,
            arguments = Detail.Category.arguments
        ) {
            val categoryName = it.arguments?.getString(NavParams.CATEGORY_NAME.argName).orEmpty()
            CategoryDetailScreen(
                categoryName = categoryName
            )
        }
        composable(
            route = Detail.Meal.route,
            arguments = Detail.Meal.arguments
        ) {
            val mealId = it.arguments?.getString(NavParams.ID.argName).orEmpty()
            MealDetailScreen(
                mealId = mealId
            )
        }
    }
}