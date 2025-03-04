package com.example.mydiary.presentation.adapters.holders

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mydiary.databinding.StatisticsMoodDuringDayBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.MoodyDuringDayModel

class MoodyDuringDayHolder(
    binding: StatisticsMoodDuringDayBinding,
) : BaseViewHolder<StatisticsMoodDuringDayBinding, MoodyDuringDayModel>(binding) {
    @SuppressLint("SetTextI18n")
    override fun onBinding(item: MoodyDuringDayModel) = with(binding) {
        var linear = -1
        for (j in 0 until root.childCount) {
            var child = root.getChildAt(j)
            if (child is LinearLayout) {
                linear++
                if (item.time[linear] == 0) {
                    child = child.getChildAt((child).childCount - 1)
                    child.layoutParams =
                        (child.layoutParams as LinearLayout.LayoutParams).apply {
                            weight = 1f
                        }
                    continue
                }
                for (i in 0 until child.childCount - 1) {
                    val childText = child.getChildAt(i)
                    if (childText is TextView) {
                        if (item.timePercent[linear][i] == 0f) {
                            childText.visibility = View.GONE
                            continue
                        }

                        childText.layoutParams =
                            (childText.layoutParams as LinearLayout.LayoutParams).apply {
                                weight = item.timePercent[linear][i]
                            }
                        childText.text = (item.timePercent[linear][i]* 100).toInt().toString() + "%"
                    }
                }
            }
        }
        earnMorning.text = item.time[0].toString()
        morning.text = item.time[1].toString()
        day.text = item.time[2].toString()
        evening.text = item.time[3].toString()
        lateEvening.text = item.time[4].toString()


    }
}