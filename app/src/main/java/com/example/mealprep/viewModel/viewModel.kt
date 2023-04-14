package com.example.mealprep.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealprep.data.model.Meal
import com.example.mealprep.database.MealDatabase
import com.example.mealprep.repositary.MealRepositary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class viewModel(application: Application): AndroidViewModel(application) {
    val readAllData : LiveData<List<Meal>>
//    var specificMealData : LiveData<List<Meal?>>? = MutableLiveData()

    private val repositary : MealRepositary
    init {
        val mealDao = MealDatabase.getDatabase(application).mealDao()
        repositary = MealRepositary(mealDao)
        readAllData = repositary.readLiveData

    }
    fun addMeal(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            repositary.addMeal(meal)
        }
    }
    fun deleteAllMeals(){
        viewModelScope.launch (Dispatchers.IO ){
            repositary.deleteMeals()
        }
    }
     suspend fun getMealsByMealName(mealN :String): List<Meal?> {
         return withContext(Dispatchers.IO) {
             repositary.getMealsByMealName(mealN)
         }
    }


}