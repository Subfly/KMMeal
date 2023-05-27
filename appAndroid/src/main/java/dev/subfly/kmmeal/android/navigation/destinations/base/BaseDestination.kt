package dev.subfly.kmmeal.android.navigation.destinations.base

import androidx.navigation.NamedNavArgument
import dev.subfly.kmmeal.android.common.utils.enums.NavParams

abstract class BaseDestination {
    abstract val route: String
    abstract val arguments: List<NamedNavArgument>

    internal fun createRoute(
        route: String,
        parameters: List<NavParams> = emptyList()
    ): String {
        var head = "$route?"
        parameters.forEachIndexed { index, param ->
            head += param.routeParameter
            if(index != parameters.lastIndex)
                head += "&"
        }
        return head
    }

    internal fun createRouteWithParams(
        route: String,
        arguments: List<NavParams> = emptyList(),
        params: List<String> = emptyList()
    ): String {
        require(arguments.size == params.size)
        var head = "$route?"
        arguments.forEachIndexed { index, name ->
            head += "${name.argName}=${params[index]}"
            if(index != arguments.lastIndex)
                head += "&"
        }
        return head
    }
}