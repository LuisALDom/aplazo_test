package com.swyt.aplazotest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.swyt.aplazotest.R
import com.swyt.aplazotest.actions.MealsAction
import com.swyt.aplazotest.base.BaseFragment
import com.swyt.aplazotest.databinding.FragmentMealsBinding
import com.swyt.aplazotest.ui.viewholder.MealViewHolder
import com.swyt.aplazotest.viewModel.MealsViewModel
import com.swyt.aplazotest.viewModel.ViewModelFactory
import com.swyt.provider.model.ListMealsResponse
import com.xwray.groupie.GroupieAdapter
import org.koin.android.ext.android.inject

class MealsFragment : BaseFragment() {

    private val binding: FragmentMealsBinding by lazy {
        FragmentMealsBinding.inflate(LayoutInflater.from(context), null, false)
    }
    private val viewModelFactory: ViewModelFactory by inject()
    private val viewModel: MealsViewModel by navGraphViewModels(R.id.nav_graph) { viewModelFactory }
    private val mealAdapter: GroupieAdapter by lazy { GroupieAdapter() }
    private val args: MealsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LADB","------>>>>> $args")
        viewModel.getListMeals(args.category?.nameCategory!!)
        setUpRecycler()
        setUpObservable()
    }

    private fun setUpRecycler() {
        binding.rvMeals.adapter = mealAdapter
    }

    private fun setUpObservable() {
        viewModel.listMeals.observerInside { meals ->
            when (meals) {
                is MealsAction.MealData -> handlerMeals(meals.data)
                is MealsAction.Error -> handlerError(meals.message)
            }
        }
    }

    private fun handlerMeals(data: ListMealsResponse) {
        hideProgressBar()
        mealAdapter.clear()
        mealAdapter.addAll(data.meals.map { meal ->
            MealViewHolder(meal) { selected ->
                Log.d("LADB", "-----<><<<<<<<<<<>>><>> $meal")
            }
        })
    }

    private fun handlerError(message: String) {
        showSnackBarError(message)
        hideProgressBar()
    }

    override fun onBackPressFunction() {
        super.onBackPressFunction()
        findNavController().navigateUp()
    }
}