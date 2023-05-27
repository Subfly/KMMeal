package dev.subfly.kmmeal.core.base

interface BaseState<T> {
    val isLoading: Boolean
    val error: String
    val data: T
}
