package com.example.mydiary.presentation.adapters.holders

import android.view.LayoutInflater
import android.widget.TextView
import com.example.mydiary.R
import com.example.mydiary.databinding.LogbookTopBarBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.LogbookTopBarModel

class LogbookTopBarHolder(
    binding: LogbookTopBarBinding,
) : BaseViewHolder<LogbookTopBarBinding, LogbookTopBarModel>(binding) {
    override fun onBinding(item: LogbookTopBarModel) = with(binding) {
        topLogbookBarFlexbox.removeAllViews()

        val view = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.top_bar_item_one, topLogbookBarFlexbox, false)

        var textView = view.findViewById<TextView>(R.id.logs)
        textView.text = item.logs

        topLogbookBarFlexbox.addView(view)

        val view1 = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.top_bar_item_two, topLogbookBarFlexbox, false)

        textView = view1.findViewById(R.id.data_time)
        textView.text = item.logsInTime.first

        textView = view1.findViewById(R.id.count_logs)
        textView.text = item.logsInTime.first
        topLogbookBarFlexbox.addView(view1)

        val view2 = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.top_bar_item_two, topLogbookBarFlexbox, false)

        textView = view2.findViewById(R.id.data_time)
        textView.text = item.streak.first

        textView = view2.findViewById(R.id.count_logs)
        textView.text = item.streak.first
        topLogbookBarFlexbox.addView(view2)


    }
}