package com.swyt.aplazotest.ui.viewholder

import android.view.View
import com.swyt.aplazotest.R
import com.swyt.aplazotest.databinding.LayoutItemCategoryBinding
import com.swyt.aplazotest.utils.load
import com.swyt.provider.model.Category
import com.xwray.groupie.viewbinding.BindableItem

class CategoryViewHolder(
    private val category: Category,
    private val funClick: (Category) -> Unit
): BindableItem<LayoutItemCategoryBinding>() {
    override fun bind(viewBinding: LayoutItemCategoryBinding, position: Int) {
        viewBinding.apply {
            ivCategory.load(category.imageCategory!!)
            tvNameCategory.text = category.nameCategory
            cCategory.setOnClickListener { funClick.invoke(category) }
        }
    }

    override fun getLayout(): Int = R.layout.layout_item_category

    override fun initializeViewBinding(view: View): LayoutItemCategoryBinding =
        LayoutItemCategoryBinding.bind(view)

}