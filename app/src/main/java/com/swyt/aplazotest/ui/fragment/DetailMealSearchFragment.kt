package com.swyt.aplazotest.ui.fragment

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.swyt.aplazotest.base.BaseFragment
import com.swyt.aplazotest.databinding.FragmentDetailBinding
import com.swyt.aplazotest.utils.load
import com.swyt.provider.model.DetailMeal

class DetailMealSearchFragment: BaseFragment() {

    private val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(LayoutInflater.from(context), null, false)
    }
    private val args: DetailMealSearchFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideProgressBar()
        setUpDataOnScreen(args.detailSearch)
    }

    private fun setUpDataOnScreen(detailSearch: DetailMeal?) {
        binding.apply {
            detailSearch?.apply {
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

    override fun onBackPressFunction() {
        super.onBackPressFunction()
        findNavController().navigateUp()
    }
}