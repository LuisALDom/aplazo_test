package com.swyt.aplazotest.ui.fragment

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.swyt.aplazotest.R
import com.swyt.aplazotest.actions.CategoriesAction
import com.swyt.aplazotest.actions.RandomMealAction
import com.swyt.aplazotest.actions.SearchMealAction
import com.swyt.aplazotest.base.BaseFragment
import com.swyt.aplazotest.databinding.FragmentCategoriesBinding
import com.swyt.aplazotest.ui.viewholder.CategoryViewHolder
import com.swyt.aplazotest.ui.viewholder.SearchViewHolder
import com.swyt.aplazotest.utils.hide
import com.swyt.aplazotest.utils.load
import com.swyt.aplazotest.utils.show
import com.swyt.aplazotest.viewModel.CategoriesViewModel
import com.swyt.aplazotest.viewModel.ViewModelFactory
import com.swyt.provider.model.DetailMealResponse
import com.swyt.provider.model.ListCategoriesResponse
import com.xwray.groupie.GroupieAdapter
import org.koin.android.ext.android.inject

class CategoriesFragment : BaseFragment() {

    private val binding: FragmentCategoriesBinding by lazy {
        FragmentCategoriesBinding.inflate(LayoutInflater.from(context), null, false)
    }
    private val viewModelFactory: ViewModelFactory by inject()
    private val viewModel: CategoriesViewModel by navGraphViewModels(R.id.nav_graph) { viewModelFactory }
    private val categoryAdapter: GroupieAdapter by lazy { GroupieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        viewModel.getListCategories()
        viewModel.randomMeal()
        setUpOnRefresh()
        setUpSearch()
        setUpRecycler()
        setUpObservable()
    }

    private fun setUpOnRefresh() {
        binding.apply {
            srlCategories.apply {
                setColorSchemeColors(requireContext().getColor(R.color.blue))
                setOnRefreshListener {
                    cInformativeScreen.hide()
                    cvCategories.show()
                    viewModel.getListCategories()
                    isRefreshing = false
                    svSearch.setQuery("", true)
                    svSearch.clearFocus()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            svSearch.setQuery("", true)
            svSearch.clearFocus()
        }
    }

    private fun setUpSearch() {
        binding.svSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                categoryAdapter.clear()
                if (!query.isNullOrEmpty()){
                    showProgressBar()
                    viewModel.searchMealByLetter(query)
                } else {
                    showProgressBar()
                    viewModel.getListCategories()
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                categoryAdapter.clear()
                if (!query.isNullOrEmpty()){
                    showProgressBar()
                    viewModel.searchMealByLetter(query)
                } else {
                    showProgressBar()
                    viewModel.getListCategories()
                }
                return false
            }
        })
    }

    private fun setUpRecycler() {
        binding.rvCategories.adapter = categoryAdapter
    }

    private fun setUpObservable() {
        viewModel.apply {
            listCategories.observerInside { categories ->
                when (categories) {
                    is CategoriesAction.CategoriesData -> handlerCategories(categories.data)
                    is CategoriesAction.Error -> handlerError(categories.message)
                }
            }
            searchMeal.observerInside { search ->
                when (search) {
                    is SearchMealAction.SearchMealData -> handlerSearch(search.data)
                    is SearchMealAction.Error -> handlerErrorSearch()
                }
            }
            randomMeal.observerInside { meal->
                when (meal) {
                    is RandomMealAction.RandomMealData -> handlerRandom(meal.data)
                    is RandomMealAction.Error -> handlerErrorRandom()
                }
            }
        }
    }

    private fun handlerCategories(data: ListCategoriesResponse) {
        hideProgressBar()
        categoryAdapter.clear()
        categoryAdapter.addAll(data.categories.map { category ->
           CategoryViewHolder(category) { selected ->
               showProgressBar()
               findNavController().navigate(R.id.MealsFragment, bundleOf(Pair("category", selected)))
           }
        })
    }

    private fun handlerSearch(data: DetailMealResponse) {
        hideProgressBar()
        categoryAdapter.clear()
        binding.apply {
            if (data.meal.isNullOrEmpty()) {
                cInformativeScreen.show()
                cvCategories.hide()
                tvInformativeText.text = context?.getText(com.swyt.style.R.string.not_find_result)
            } else {
                cInformativeScreen.hide()
                cvCategories.show()
                categoryAdapter.addAll(data.meal.map { meal ->
                    SearchViewHolder(meal) { selected ->
                        showProgressBar()
                        findNavController().navigate(R.id.DetailSearchFragment, bundleOf(Pair("detailSearch", selected)))
                    }
                })
            }
        }
    }

    private fun handlerRandom(data: DetailMealResponse) {
        binding.apply {
            if (data.meal.isNotEmpty()) {
                data.meal.first().apply {
                    ivRandom.load(imageMeal!!)
                    tvNameRandom.text = nameMeal

                    tvYoutubeRandom.movementMethod = LinkMovementMethod.getInstance()
                    tvYoutubeRandom.text = Html.fromHtml("<a href='$linkYoutube'>$linkYoutube</a>",
                        Html.FROM_HTML_MODE_LEGACY)

                    tvSourceRandom.movementMethod = LinkMovementMethod.getInstance()
                    tvSourceRandom.text = Html.fromHtml("<a href='$source'>$source</a>",
                        Html.FROM_HTML_MODE_LEGACY)
                }
            } else {
                handlerErrorRandom()
            }
        }
    }

    private fun handlerError(message: String) {
        showSnackBarError(message)
        hideProgressBar()
    }

    private fun handlerErrorSearch() {
        hideProgressBar()
        binding.apply {
            cInformativeScreen.show()
            cvCategories.hide()
            tvInformativeText.text = context?.getText(com.swyt.style.R.string.not_find_result)
        }
    }

    private fun handlerErrorRandom() {
        binding.apply {
            ivRandom.setImageResource(com.swyt.style.R.drawable.ic_no_wifi)
            tvNameRandom.text = context?.getText(com.swyt.style.R.string.no_data)
            tvYoutubeRandom.text = context?.getText(com.swyt.style.R.string.not_find_result)
            tvSourceRandom.text = context?.getText(com.swyt.style.R.string.not_find_result)
        }
    }

    override fun onBackPressFunction() {
        super.onBackPressFunction()
        finishParentActivity()
    }

}