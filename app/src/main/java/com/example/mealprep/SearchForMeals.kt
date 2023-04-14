package com.example.mealprep

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealprep.data.model.Meal
import com.example.mealprep.databinding.FragmentSearchForMealsBinding
import com.example.mealprep.viewModel.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class SearchForMeals : Fragment() {
    private lateinit var binding: FragmentSearchForMealsBinding
    //    initiate view Model
    lateinit var mViewModel : viewModel

    //    initiate the recycler view
    private lateinit var recyclerView: RecyclerView
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_for_meals, container,false)
        mViewModel = ViewModelProvider(this).get(viewModel::class.java)
//        recycler view
        recyclerView = binding.dbMealRCView
        recyclerView.layoutManager =  LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(false)


        binding.Search.setOnClickListener {
            val search = binding.editTextTextPersonName.text.toString()
            val mealad : MutableList<Meal> = getDataFromDatabase(search)
            recyclerView.adapter = MealAdapter(mealad)

        }


        return binding.root
    }
    fun getDataFromDatabase(data :String):MutableList<Meal>{
        var mealList = mutableListOf<Meal>()
        runBlocking{
            launch {
                withContext(Dispatchers.IO){
                    val meal = mViewModel.getMealsByMealName(data)
                    mealList = meal.filterNotNull().toMutableList()
                    Log.d("meal",mealList.toString())

                }
            }

        }
        return mealList
    }


}