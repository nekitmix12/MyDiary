package com.example.mydiary.presentation.adapters.holders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.databinding.StatisticsEmotionsBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.models.VerticalItemWithLabelModel

class VerticalItemWithLabelHolder(
    binding: StatisticsEmotionsBinding,
    delegates: List<Delegate<*, *>>,
    private val decorators: List<RecyclerView.ItemDecoration>,
) : BaseViewHolder<StatisticsEmotionsBinding, VerticalItemWithLabelModel>(binding) {
    private val delegateAdapter = AdapterWithDelegates(delegates)

    init {

        with(binding.statisticsRecyclerEmotionByDay) {
            adapter = delegateAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            decorators.forEach { decorator ->
                if (itemDecorationCount == 0 || !hasDecoration(decorator)) {
                    addItemDecoration(decorator)
                }
            }
        }
    }

    private fun RecyclerView.hasDecoration(decoration: RecyclerView.ItemDecoration): Boolean {
        for (i in 0 until itemDecorationCount) {
            if (getItemDecorationAt(i) == decoration) return true
        }
        return false
    }

    override fun onBinding(item: VerticalItemWithLabelModel) {
        super.onBinding(item)
        binding.emotionLabel.text = item.label
        delegateAdapter.submitList(item.items)
    }
}