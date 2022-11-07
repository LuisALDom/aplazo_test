package com.swyt.aplazotest.ui.viewholder

import android.view.View
import com.swyt.aplazotest.R
import com.swyt.aplazotest.databinding.LayoutItemMealBinding
import com.swyt.aplazotest.utils.load
import com.swyt.provider.model.Meal
import com.xwray.groupie.viewbinding.BindableItem

class MealViewHolder(
    private val meal: Meal,
    private val funClick: (Meal) -> Unit
): BindableItem<LayoutItemMealBinding>() {
    override fun bind(viewBinding: LayoutItemMealBinding, position: Int) {
        viewBinding.apply {
            ivMeal.load(meal.imageMeal!!)
            tvNameMeal.text = meal.nameMeal
            cMeal.setOnClickListener { funClick.invoke(meal) }
        }
    }

    override fun getLayout(): Int = R.layout.layout_item_meal

    override fun initializeViewBinding(view: View): LayoutItemMealBinding =
        LayoutItemMealBinding.bind(view)

}