package com.example.mealprep.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mealprep.data.model.Meal


@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMeal(meal: Meal)

    @Query("SELECT * FROM meal_table")
    fun readAllData(): LiveData<List<Meal>>

    @Query("DELETE from meal_table")
    fun deleteAllData()

    @Query("SELECT * FROM meal_table WHERE Lower(Meal) Like '%' || Lower(:specificValue ) || '%'  ")
    fun getMealsBySpecificValue(specificValue: String?): List<Meal?>

}