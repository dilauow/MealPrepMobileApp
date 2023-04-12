package com.example.mealprep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mealprep.data.model.Meal
import com.example.mealprep.databinding.FragmentSearchMealsByIngredientBinding
import com.example.mealprep.utilities.utils
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader


class SearchMealsByIngredient : Fragment() {
    lateinit var binding : FragmentSearchMealsByIngredientBinding

    var idList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_meals_by_ingredient, container,false)
        binding.search.setOnClickListener {
            print("button pressed")
            var serviceLink = "https://www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast"
            val stb = getDataFromWebService(serviceLink)
            idList = utils().parseIngredientDataToJSON(stb)
            retrieveMealsFromIds(idList)

        }

        return binding.root
    }

    fun retrieveMealsFromIds(idList: MutableList<String>){
        val tempMealDataArray =  mutableListOf<Meal>()
        for (id in idList){
            var link = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=$id"
            val stb = getDataFromWebService(link)
            tempMealDataArray.add(utils().parseMealDataToMealObj(stb))
        }
        binding.mealText.setText(tempMealDataArray.toString())

    }


    fun getDataFromWebService(link :String):StringBuilder{
        var stb = StringBuilder()
        val url_string = link
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        runBlocking {
            launch {
                // run the code of the coroutine in a new thread
                withContext(Dispatchers.IO) {
                    var bf = BufferedReader(InputStreamReader(con.inputStream))
                    var line: String? = bf.readLine()
                    while (line != null) {
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }

                }
            }
        }
       return stb
    }




}