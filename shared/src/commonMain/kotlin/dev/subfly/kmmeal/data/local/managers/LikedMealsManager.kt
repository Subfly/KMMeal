package dev.subfly.kmmeal.data.local.managers

import dev.subfly.kmmeal.data.local.database.LikedMealsDatabase
import dev.subfly.kmmeal.data.local.entities.LikedMealEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LikedMealsManager(
    private val realm: Realm
): LikedMealsDatabase {
    override fun getAllLikedMeals(): Flow<List<LikedMealEntity>> {
        return realm.query<LikedMealEntity>().asFlow().map { results -> results.list }
    }

    override suspend fun getLikedMeal(mealId: String): LikedMealEntity {
        return realm.query<LikedMealEntity>("id == $mealId").find().first()
    }

    override suspend fun likeMeal(meal: LikedMealEntity) {
        realm.write {
            copyToRealm(meal)
        }
    }

    override suspend fun removeFromLikedMeals(meal: LikedMealEntity) {
        realm.write {
            val obj = this.query<LikedMealEntity>("id == '${meal.id}'").find().first()
            delete(obj)
        }
    }

    override suspend fun deleteAllLikedMeals() {
        realm.write {
            deleteAll()
        }
    }

}