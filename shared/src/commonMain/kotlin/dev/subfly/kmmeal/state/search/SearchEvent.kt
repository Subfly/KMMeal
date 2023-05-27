package dev.subfly.kmmeal.state.search

import dev.subfly.kmmeal.core.utils.enums.SearchFilterType

sealed class SearchEvent {
    data class OnQueryChanged(val newQuery: String): SearchEvent()
    data class OnFilterChanged(val newFilter: SearchFilterType): SearchEvent()
}
