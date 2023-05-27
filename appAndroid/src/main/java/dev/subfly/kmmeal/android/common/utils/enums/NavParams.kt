package dev.subfly.kmmeal.android.common.utils.enums

enum class NavParams(val argName: String) {
    ID("id"),
    CATEGORY_NAME("category_name");

    val routeParameter: String = "${this.argName}={${this.argName}}"
}