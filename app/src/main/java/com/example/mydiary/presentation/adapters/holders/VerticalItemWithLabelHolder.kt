package com.example.mydiary.presentation.adapters.holders

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.R
import com.example.mydiary.databinding.StatisticsEmotionsBinding
import com.example.mydiary.presentation.adapters.AdapterWithDelegates
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.adapters.Delegate
import com.example.mydiary.presentation.adapters.decorators.LineItemDecoration
import com.example.mydiary.presentation.models.Gap
import com.example.mydiary.presentation.models.VerticalItemWithLabelModel

class VerticalItemWithLabelHolder(
    binding: StatisticsEmotionsBinding,
    delegates: List<Delegate<*, *>>,
) : BaseViewHolder<StatisticsEmotionsBinding, VerticalItemWithLabelModel>(binding) {
    private val delegateAdapter = AdapterWithDelegates(delegates)

    init {
        with(binding.statisticsRecyclerEmotionByDay) {
            adapter = delegateAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(
                LineItemDecoration(
                    Gap(
                        color = context.getColor(R.color.grey),
                        height = 1.dpToPx(context),
                        onDivider = 12.dpToPx(context),
                        underDivider = 12.dpToPx(context),
                    ),
                    R.layout.emotion_logs
                )
            )

        }
    }

    override fun onBinding(item: VerticalItemWithLabelModel) {
        super.onBinding(item)
        binding.emotionLabel.text = item.label
        delegateAdapter.submitList(item.items)
    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}