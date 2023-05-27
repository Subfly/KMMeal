package dev.subfly.kmmeal.data.local.entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey

class IngredientEntity: RealmObject {
     @PrimaryKey var id: RealmUUID = RealmUUID.random()
    var name: String = ""
    var count: String = ""
    var imageUrl: String = ""
}