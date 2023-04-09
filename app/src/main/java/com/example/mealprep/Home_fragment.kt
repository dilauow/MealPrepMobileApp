package com.example.mealprep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.mealprep.databinding.FragmentHomeFragmentBinding

class Home_fragment : Fragment() {

    lateinit var binding : FragmentHomeFragmentBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_fragment, container,false)

        binding.mealsbyIng.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_fragment_to_searchMealsByIngredient)
        }
        binding.searchMeals.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home_fragment_to_searchForMeals)
        }

        return binding.root

    }

}