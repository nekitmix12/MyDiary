package com.example.mydiary.presentation.adapters.holders

import android.widget.TextView
import com.example.mydiary.R
import com.example.mydiary.databinding.NotesQuestionsBlockBinding
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.AnswerModel
import com.example.mydiary.presentation.models.QuestionBlockModel

class QuestionsBlockHolder(
    binding: NotesQuestionsBlockBinding,
    private val onAnswerClick: (AnswerModel) -> Unit,
    private val onAddAnswerButtonClick: () -> Unit,
) :
    BaseViewHolder<NotesQuestionsBlockBinding, QuestionBlockModel>(binding) {
    override fun onBinding(item: QuestionBlockModel) = with(binding) {
        labelAnswer.text = item.label

        for (i in 0 until flexbox.childCount - 1) {
            val child = flexbox.getChildAt(i)
            child.setOnClickListener { onAnswerClick(item.answers[i]) }
            val text = child.findViewById<TextView>(R.id.include_text)
            if (text != null) {
                text.isSelected = item.answers[i].selected
                text.text = item.answers[i].text
            }
        }

        val addButton = flexbox.getChildAt(flexbox.childCount - 1)
        addButton.setOnClickListener { onAddAnswerButtonClick }
    }
}