package com.example.mealprep.repositary

import androidx.lifecycle.LiveData
import com.example.mealprep.dao.MealDao
import com.example.mealprep.data.model.Meal

class MealRepositary(private val mealDao : MealDao) {
    val readLiveData : LiveData<List<Meal>> = mealDao.readAllData()
    suspend fun addMeal(meal: Meal){
        mealDao.addMeal(meal)
    }
    suspend fun deleteMeals(){
        mealDao.deleteAllData()
    }
    suspend fun getMealsByMealName(mealN :String): List<Meal?> {
        return mealDao.getMealsBySpecificValue(mealN)
    }

}


