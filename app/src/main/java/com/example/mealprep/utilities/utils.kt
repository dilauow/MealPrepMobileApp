package com.example.mealprep.utilities
import com.example.mealprep.controllers.userControllers
import com.example.mealprep.data.model.Meal
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class utils {
     fun parseIngredientDataToJSON(stb: java.lang.StringBuilder):MutableList<String>{
        val json = JSONObject(stb.toString())
        // Information about all the books extracted by this function
        var allMealItems = mutableListOf<String>()
        var jsonArray: JSONArray = json.getJSONArray("meals")
        for (i in 0..jsonArray.length()-1) {
            val meal: JSONObject = jsonArray[i] as JSONObject // this is a json object
            val idmeal = meal["idMeal"] as String
            allMealItems.add(idmeal)

        }
        print("printing meal ids")
        print(allMealItems)
        return allMealItems

    }
    fun parseMealDataToMealObj(stb: java.lang.StringBuilder):Meal{
        val json = JSONObject(stb.toString())
        var mealArray: JSONArray = json.getJSONArray("meals")
        var mealObj : JSONObject = mealArray.getJSONObject(0)
        val mealdataObj: Meal = userControllers().convertWebServiceJSONObjectsToMealObj(mealObj)
        return mealdataObj
    }



}