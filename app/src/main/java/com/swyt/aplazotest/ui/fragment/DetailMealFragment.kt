package com.swyt.aplazotest.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.swyt.aplazotest.R
import com.swyt.aplazotest.actions.DetailMealAction
import com.swyt.aplazotest.base.BaseFragment
import com.swyt.aplazotest.databinding.FragmentDetailBinding
import com.swyt.aplazotest.utils.load
import com.swyt.aplazotest.viewModel.DetailMealViewModel
import com.swyt.aplazotest.viewModel.ViewModelFactory
import com.swyt.provider.model.DetailMealResponse
import org.koin.android.ext.android.inject

class DetailMealFragment : BaseFragment() {

    private val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(LayoutInflater.from(context), null, false)
    }
    private val viewModelFactory: ViewModelFactory by inject()
    private val viewModel: DetailMealViewModel by navGraphViewModels(R.id.nav_graph) { viewModelFactory }
    private val args: DetailMealFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailMeal(args.detail?.idMeal!!)
        setUpObservable()
    }

    private fun setUpObservable() {
        viewModel.detailMeal.observerInside { detail ->
            when (detail) {
                is DetailMealAction.DetailMealData -> handlerDetail(detail.data)
                is DetailMealAction.Error -> handlerError(detail.message)
            }
        }
    }

    private fun handlerDetail(data: DetailMealResponse) {
        binding.apply {
            if (data.meal.isNotEmpty()) {
                data.meal.first().apply {
                    ivDetail.load(imageMeal!!)
                    tvMeal.text = nameMeal
                    tvInstructions.text = instructions

                    tvYoutube.movementMethod = LinkMovementMethod.getInstance()
                    tvYoutube.text = Html.fromHtml("<a href='$linkYoutube'>$linkYoutube</a>",
                        Html.FROM_HTML_MODE_LEGACY)

                    tvSource.movementMethod = LinkMovementMethod.getInstance()
                    tvSource.text = Html.fromHtml("<a href='$source'>$source</a>",
                        Html.FROM_HTML_MODE_LEGACY)

                }
            }
        }
        hideProgressBar()
        viewModel.detailMeal.removeObservers(viewLifecycleOwner)
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