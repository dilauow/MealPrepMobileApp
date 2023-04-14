package com.example.mealprep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mealprep.controllers.userControllers
import com.example.mealprep.databinding.FragmentHomeFragmentBinding
import com.example.mealprep.datafiles.MealsData
import com.example.mealprep.utilities.utils
import com.example.mealprep.viewModel.viewModel


class Home_fragment : Fragment() {

    lateinit var binding : FragmentHomeFragmentBinding
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

//        initiate controllers
        val uc = userControllers()
//        initiate view model
        mViewModel = ViewModelProvider(this).get(viewModel::class.java)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_fragment, container,false)

        binding.mealsbyIng.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_fragment_to_searchMealsByIngredient)
        }
        binding.searchMeals.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_fragment_to_searchForMeals)
//
        }

        binding.addtoDB.setOnClickListener {
            val mealsJ = MealsData().mealData
            val mealList = uc.convertAllJsonObjectsToMeals(mealsJ)
            for (meal in mealList){
                mViewModel.addMeal(meal)
                print(meal.Ingredients)
            }

            Toast.makeText(requireContext(), "Data Added", Toast.LENGTH_SHORT).show()
        }

        return binding.root

    }




}