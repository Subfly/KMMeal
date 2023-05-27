package dev.subfly.kmmeal

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform