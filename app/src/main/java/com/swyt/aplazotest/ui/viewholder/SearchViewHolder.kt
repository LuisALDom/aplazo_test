package com.swyt.aplazotest.ui.viewholder

import android.view.View
import com.swyt.aplazotest.R
import com.swyt.aplazotest.databinding.LayoutItemCategoryBinding
import com.swyt.aplazotest.utils.load
import com.swyt.provider.model.DetailMeal
import com.xwray.groupie.viewbinding.BindableItem

class SearchViewHolder(
    private val meal: DetailMeal,
    private val funClick: (DetailMeal) -> Unit
    ): BindableItem<LayoutItemCategoryBinding>() {
    override fun bind(viewBinding: LayoutItemCategoryBinding, position: Int) {
        viewBinding.apply {
            ivCategory.load(meal.imageMeal!!)
            tvNameCategory.text = meal.nameMeal
            cCategory.setOnClickListener { funClick.invoke(meal) }
        }
    }

    override fun getLayout(): Int = R.layout.layout_item_category

    override fun initializeViewBinding(view: View): LayoutItemCategoryBinding =
        LayoutItemCategoryBinding.bind(view)
}