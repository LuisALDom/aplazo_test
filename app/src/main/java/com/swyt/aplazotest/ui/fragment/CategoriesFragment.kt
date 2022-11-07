package com.swyt.aplazotest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.swyt.aplazotest.R
import com.swyt.aplazotest.actions.CategoriesAction
import com.swyt.aplazotest.base.BaseFragment
import com.swyt.aplazotest.databinding.FragmentCategoriesBinding
import com.swyt.aplazotest.viewModel.CategoriesViewModel
import com.swyt.aplazotest.viewModel.ViewModelFactory
import com.swyt.provider.model.ListCategoriesResponse
import org.koin.android.ext.android.inject

class CategoriesFragment : BaseFragment() {

    private val binding: FragmentCategoriesBinding by lazy {
        FragmentCategoriesBinding.inflate(LayoutInflater.from(context), null, false)
    }
    private val viewModelFactory: ViewModelFactory by inject()
    private val viewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_graph) {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        setUpObservable()
    }

    private fun setUpObservable() {
        viewModel.apply {
            listCategories.observerInside { categories ->
                when (categories) {
                    is CategoriesAction.CategoriesData -> handlerCategories(categories.data)
                    is CategoriesAction.Error -> handlerError(categories.message)
                }
            }
        }
    }

    private fun handlerCategories(data: ListCategoriesResponse) {
        Log.d("LADB","data....> ${data.categories}")
        hideProgressBar()
    }

    private fun handlerError(message: String) {
        showSnackBarError(message)
        hideProgressBar()
    }

    override fun onBackPressFunction() {
        super.onBackPressFunction()
        finishParentActivity()
    }

}