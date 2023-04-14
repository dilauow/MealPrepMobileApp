package com.example.mealprep.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mealprep.dao.MealDao
import com.example.mealprep.data.model.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao() : MealDao

    companion object{
        @Volatile
        private var DBInstance : MealDatabase? =null
        fun getDatabase(context: Context): MealDatabase{
            val tempInstance = DBInstance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "meal_database"
                ).build()
                DBInstance = instance
                return instance
            }
        }
    }

}
