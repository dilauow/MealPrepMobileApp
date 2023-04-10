package com.example.mealprep.controllers

import com.example.mealprep.data.model.Meal
import org.json.JSONObject

class userControllers {
    private fun convertJsonObjectToMeal(mealJSONObject: JSONObject) : Meal{
        val ingredients = mutableListOf<String>()
        val measures = mutableListOf<String>()
        mealJSONObject.keys().forEach { key ->
            if (key.startsWith("Ingredient")) {
                ingredients.add(mealJSONObject.getString(key))
            } else if (key.startsWith("Measure")) {
                measures.add(mealJSONObject.getString(key))
            }
        }
        val strIngredients = listtoString(ingredients)
        val strMeasures = listtoString(measures)

        val meal = Meal(0,mealJSONObject.getString("Meal"),mealJSONObject.getString("DrinkAlternate"),mealJSONObject.getString("Category"),mealJSONObject.getString("Area"),mealJSONObject.getString("Instructions"),mealJSONObject.getString("Tags"),mealJSONObject.getString("Youtube"),strIngredients,strMeasures)
        return meal
    }
    fun listtoString(value: List<String>): String {
        return value.joinToString(",")
    }

    public fun convertAllJsonObjectsToMeals(mealList: List<String>): List<Meal>{
        val mealdataclassList = mutableListOf<Meal>()
        for (meals in mealList){
            var mealObj = convertJsonObjectToMeal(JSONObject(meals))
            mealdataclassList.add(mealObj)
        }
        return mealdataclassList
    }

    companion object {


    }
}