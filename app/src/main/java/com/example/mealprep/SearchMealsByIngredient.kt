package com.example.mealprep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealprep.data.model.Meal
import com.example.mealprep.databinding.FragmentSearchMealsByIngredientBinding
import com.example.mealprep.utilities.utils
import com.example.mealprep.viewModel.viewModel
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
    lateinit var mealObjectDataMutableList : MutableList<Meal>

//    initiate the recycler view
    private lateinit var recyclerView: RecyclerView

//    initiate view Model
    lateinit var mViewModel : viewModel
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
        recyclerView = binding.mealsViewRecyclerView
        recyclerView.layoutManager =  LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(false)

        mViewModel = ViewModelProvider(this).get(viewModel::class.java)


        binding.search.setOnClickListener {
//            get from edit text
            val searchedText =binding.editTextViewSearchIngredient.text.toString()
            print("button pressed")

            var serviceLink = "https://www.themealdb.com/api/json/v1/1/filter.php?i=${searchedText}"
            val stb = getDataFromWebService(serviceLink)
            if(JSONObject(stb.toString()).get("meals").equals(null)){
                print("Nodata")
                Toast.makeText(context, "No Meals that has ingredient $searchedText", Toast.LENGTH_SHORT).show()
            }
            else{
                idList = utils().parseIngredientDataToJSON(stb)
                mealObjectDataMutableList= retrieveMealsFromIds(idList)
                recyclerView.adapter =MealAdapter(mealObjectDataMutableList)
            }

        }
        binding.databaseAdd.setOnClickListener {
            val mealList = mealObjectDataMutableList
            if (mealList.isEmpty()){
                Toast.makeText(context, "No meal to add", Toast.LENGTH_SHORT).show()

            }
            else{
                for (meal in mealList){
                    mViewModel.addMeal(meal)
//                    print(meal.Ingredients)
                }
            }
        }

        return binding.root
    }

    fun retrieveMealsFromIds(idList: MutableList<String>): MutableList<Meal>{
        val tempMealDataArray =  mutableListOf<Meal>()
        for (id in idList){
            var link = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=$id"
            val stb = getDataFromWebService(link)
            tempMealDataArray.add(utils().parseMealDataToMealObj(stb))
        }
//        binding.mealText.setText(tempMealDataArray.toString())
        return tempMealDataArray

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