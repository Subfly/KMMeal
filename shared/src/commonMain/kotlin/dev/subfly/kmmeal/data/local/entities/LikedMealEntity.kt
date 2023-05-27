package dev.subfly.kmmeal.data.local.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class LikedMealEntity: RealmObject {
    @PrimaryKey var id: String = ""
    var name: String = ""
    var area: String = ""
    var category: String = ""
    var imageUrl: String = ""
    var drinkAlternative: String = ""
    var ingredients: RealmList<IngredientEntity> = realmListOf()
    var instructions: RealmList<String> = realmListOf()
    var tags: RealmList<String> = realmListOf()
    var videoUrl: String = ""
}