package com.example.mydiary.presentation.adapters.holders

import android.view.LayoutInflater
import android.widget.TextView
import com.example.mydiary.R
import com.example.mydiary.databinding.NotesQuestionsBlockBinding
import com.example.mydiary.domain.model.AnswerModel
import com.example.mydiary.presentation.adapters.BaseViewHolder
import com.example.mydiary.presentation.models.QuestionBlockModel

class QuestionsBlockHolder(
    binding: NotesQuestionsBlockBinding,
    private val onAnswerClick: (AnswerModel) -> Unit,
    private val onAddAnswerButtonClick: (QuestionBlockModel) -> Unit,
) :
    BaseViewHolder<NotesQuestionsBlockBinding, QuestionBlockModel>(binding) {
    override fun onBinding(item: QuestionBlockModel) = with(binding) {
        labelAnswer.text = item.label
        val addButton = flexbox.getChildAt(flexbox.childCount - 1)
        flexbox.removeAllViews()

        val inflater = LayoutInflater.from(root.context)
        item.answers.forEach { answer ->
            val answerView = inflater.inflate(R.layout.notes_answer_option, flexbox, false)

            val textView = answerView.findViewById<TextView>(R.id.include_text)
            textView.text = answer.text
            textView.isSelected = answer.selected

            answerView.setOnClickListener {
                onAnswerClick(
                    AnswerModel(
                        id = answer.id,
                        text = answer.text,
                        questionId = item.questionId
                    )
                )
            }

            flexbox.addView(answerView)
        }
        flexbox.addView(addButton)
        addButton.setOnClickListener { onAddAnswerButtonClick(item) }
    }
}