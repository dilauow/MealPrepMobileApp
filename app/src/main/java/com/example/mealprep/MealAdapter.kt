package com.example.mealprep

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealprep.data.model.Meal

class MealAdapter(private val mealList: MutableList<Meal>): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){
        val mealName : TextView = itemView.findViewById(R.id.mealname)
        val mealCategory :TextView = itemView.findViewById(R.id.categoryVar)
        val drinkAlter : TextView =itemView.findViewById(R.id.DrinkAltVar)
        val area : TextView = itemView.findViewById(R.id.areaVar)
        val tagsM : TextView = itemView.findViewById(R.id.tagsVar)
        val youtube : TextView = itemView.findViewById(R.id.youtubeVar)
        val instructions : TextView = itemView.findViewById(R.id.instructionsVar)
        val ingAndMeasures : TextView = itemView.findViewById(R.id.ingMeasureVar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.meal_item,parent,false)
        return MealViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentItem = mealList[position]
        holder.mealName.text = currentItem.Meal
        holder.mealCategory.text = "Category: ${currentItem.Category}"
        holder.area.text = "Area: ${currentItem.Area}"
        holder.drinkAlter.text = "Drink Alternate: ${currentItem.DrinkAlternate}"
        holder.tagsM.text = "TAGS ${currentItem.Tags}"
        holder.youtube.text = currentItem.Youtube
        holder.instructions.text = currentItem.Instructions
        holder.ingAndMeasures.text = currentItem.Ingredients

    }
}