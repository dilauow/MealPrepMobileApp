package com.example.mealprep.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "meal_table")
data class Meal(
    @PrimaryKey val id: Int,
    val Meal: String,
    val DrinkAlternate: String,
    val Category: String,
    val MealThumb:String,
    val Area : String,
    val Instructions : String,
    val Tags : String,
    val Youtube: String,
    val Ingredients : String,
    val Measures : String
)


//"Meal":"Chicken & mushroom Hotpot",
//"DrinkAlternate":null,
//"Category":"Chicken",
//"Area":"British",
//"Instructions":"Heat oven to 200C\/180C fan\/gas 6.... ",
//"Tags":null,
//"Youtube":"https:\/\/www.youtube.com\/watch?v=bXKWu4GojNI",